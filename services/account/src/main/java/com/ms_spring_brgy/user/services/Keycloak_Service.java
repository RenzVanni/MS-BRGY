package com.ms_spring_brgy.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms_spring_brgy.user.constants.UserData;
import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import com.ms_spring_brgy.user.dto.LoginRequestDTO;
import com.ms_spring_brgy.user.dto.UpdateUserRequestDTO;
import com.ms_spring_brgy.user.exception.AlreadyExistsException;
import com.ms_spring_brgy.user.exception.CustomRunTimeException;
import com.ms_spring_brgy.user.exception.DoesNotExistsException;
import com.ms_spring_brgy.user.exception.EmptyException;
import com.ms_spring_brgy.user.helper.Mapper;
import com.ms_spring_brgy.user.services.components.Service_Component;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@EnableCaching
public class Keycloak_Service {
    private final Mapper mapper;

    @Value("${jwt.auth.issuerPath}")
    String issuerPath;

    @Value("${jwt.auth.client-id}")
    String clientId;

    @Value("${jwt.auth.client-secret}")
    String clientSecret;

    @Value("${jwt.auth.realm-name}")
    String realmName;

    @Value("${jwt.auth.resident-id}")
    private String residentId;

    Keycloak keycloak = Keycloak.getInstance(
            "http://localhost:8080",
            "master",
            "admin",
            "admin",
            "admin-cli"
    );

    /**
     * Fetch access token
     * @param body
     * @return
     */
    public String accessToken(LoginRequestDTO body) {
        String tokenUrl = issuerPath + "/protocol/openid-connect/token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", body.username());
        map.add("password", body.password());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            return response.getBody().get("access_token").toString();
    }

    /**
     * Fetch keycloak realm
     * @return
     */
    private RealmResource realmResource() {
        return keycloak.realm(realmName);
    }

    /**
     * Fetch client UUID
     * @return
     */
    private String fetchClientUUID() {
        return realmResource()
                .clients()
                .findByClientId(clientId)
                .get(0)
                .getId();
    }

    /**
     * Fetch all users
     * @return
     */
    public List<Auth_Response_DTO> getUsers() {
        List<UserRepresentation> userRepresentation = realmResource()
                .users()
                .list();

        return mapper.authResponseMapper(userRepresentation, realmResource(), fetchClientUUID());
    }

    /**
     * Paginate Users
     * @param page
     * @return
     */
    @Cacheable(value = "users", key = "#page")
    public Map<String, Object> paginateUsers(int page) {
        int totalUsers = realmResource().users().count();
        int totalPages = (int) Math.ceil((double) totalUsers / 3);
        int offSet = page * 3;
        List<UserRepresentation> userRepresentation = realmResource()
                .users()
                .list(offSet, 3);
        List<Auth_Response_DTO> mappedUser = mapper
                .authResponseMapper(userRepresentation, realmResource(), fetchClientUUID());

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("data", mappedUser);
        data.put("pages", totalPages);



//        return mapper.authResponseMapper(userRepresentation, realmResource(), fetchClientUUID());
        return data;
    }



    /**
     * Fetch user by ID
     * @param id
     * @return
     */
    public Auth_Response_DTO fetchUserById(String id) {
        UserRepresentation userRepresentation = realmResource()
                .users()
                .get(id)
                .toRepresentation();

        return mapper.mapUserRepresentation(userRepresentation, realmResource(), fetchClientUUID());
    }

    /**
     * Count all users
     * @return
     */
    public Integer countUsers() {
        return realmResource()
                .users()
                .count();
    }

    /**
     * Create User
     * @param body
     */
    public Map<String, String> createUser(UserData body) {
        Map<String, List<String>> attribute = new HashMap<>();
        attribute.put("resident_id", List.of(body.getResidentId().toString()));

        // set user data
        UserRepresentation user = new UserRepresentation();
        user.setUsername(body.getUsername());
        user.setEnabled(true);
        user.setEmail(body.getEmail());
        user.setAttributes(attribute);

        //create password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        if(Objects.equals(body.getPassword(), body.getConfirmPassword())) {
            credential.setValue(body.getPassword());
        } else {
            throw new RuntimeException("Password not matched!");
        }

        user.setCredentials(Collections.singletonList(credential));

        // send request
        try {

            // convert Long resident id to String
            String convertResidentId = String.valueOf(body.getResidentId());

            // fetch all users
            List<UserRepresentation> fetchUsers = realmResource()
                    .users()
                    .list();

            // map all users
            for (UserRepresentation existingUser : fetchUsers) {
                Map<String, List<String>> existingAttribute = existingUser.getAttributes();

                // throw error if no resident id
                if(Objects.isNull(existingAttribute.get("resident_id"))) {
                    throw new EmptyException("No resident ID!");
                }

                // throw error if resident id already exists
                if(existingAttribute.get("resident_id").contains(convertResidentId)){
                    throw new AlreadyExistsException("Resident ID already exists!");
                }
            }

            //client role list
            List<RoleRepresentation> clientRoles = realmResource()
                    .clients()
                    .get(fetchClientUUID())
                    .roles()
                    .list();

            // check if body role is match to client role
            boolean checkIfRoleMatch = clientRoles
                    .stream()
                    .anyMatch(role -> role.getName().equals(body.getRole()));

            // throw error if body role is not match to client role
            if(!checkIfRoleMatch) {
                throw new DoesNotExistsException("Role is not valid!");
            }

            //create user
            Response response = realmResource()
                    .users()
                    .create(user);

            //throw error if user already exist
            if(response.getStatus() == 409) {
                throw new AlreadyExistsException("User already exist");
            }

            //fetch user id
            String userId = CreatedResponseUtil.getCreatedId(response);

            //fetch role in client
            RoleRepresentation role = realmResource()
                    .clients()
                    .get(fetchClientUUID())
                    .roles()
                    .get(body.getRole())
                    .toRepresentation();

            //assign role to user
            realmResource()
                    .users()
                    .get(userId)
                    .roles()
                    .clientLevel(fetchClientUUID())
                    .add(Collections.singletonList(role));

            // return success message
            Map<String, String> message = new HashMap<>();
            message.put("success", "User Created Successfully");
            return message;

        } catch (Exception e) {
            throw new CustomRunTimeException(e.getMessage());
        }
    }

    /**
     * Update User
     * @param body
     */
    public void updateUser(UpdateUserRequestDTO body) {
        try {
            // fetch existing user
            UserResource userResource = realmResource()
                    .users()
                    .get(body.id());

            UserRepresentation existingUser = userResource.toRepresentation();

            existingUser.setEnabled(existingUser.isEnabled());
            existingUser.setFirstName(existingUser.getFirstName());
            existingUser.setLastName(existingUser.getLastName());
            existingUser.setEmailVerified(existingUser.isEmailVerified());

            List<UserRepresentation> userList = realmResource()
                    .users()
                    .list();

            // check if resident id already exists
            boolean ifResidentIdExists = userList
                    .stream()
                    .anyMatch(id -> id.getAttributes().get("resident_id").get(0).equals(body.residentId().toString()));

            // if resident id is not match to body id
            if(!existingUser.getAttributes().get("resident_id").get(0).equals(body.residentId().toString())) {

                // throw error if resident id already exists
                if (ifResidentIdExists) {
                    throw new AlreadyExistsException("Resident ID Already Exists");
                }
            }

            // set username
            if (body.username() != null && !body.username().equals(existingUser.getUsername())) {
                existingUser.setUsername(body.username());
            }

            // set email
            if (body.email() != null && !body.email().equals(existingUser.getEmail())) {
                existingUser.setEmail(body.email());
            }


            // set attributes
            Map<String, List<String>> attribute = existingUser.getAttributes();

            if(!Objects.equals(
                    attribute.get("resident_id").get(0),
                    body.residentId().toString())) {

                attribute.put("resident_id", List.of(body.residentId().toString()));

                Service_Component.updateService(
                        existingUser.getAttributes(),
                        attribute,
                        existingUser::setAttributes);
            }

            // fetch client roles
            List<RoleRepresentation> roles = realmResource()
                    .clients()
                    .get(fetchClientUUID())
                    .roles()
                    .list();

            // find if the body role have match the client roles
            boolean isRoleValid = roles
                    .stream()
                    .anyMatch(role -> body
                            .role()
                            .stream()
                            .anyMatch(r -> role.getName().equals(r)));

            // throw error if the body role does not have any match in roles
            if(!isRoleValid) {
                throw new DoesNotExistsException("Role is not Valid");
            }

            // list user roles
            List<RoleRepresentation> listUserRoles =   realmResource()
                    .users()
                    .get(body.id())
                    .roles()
                    .clientLevel(fetchClientUUID())
                    .listAll();

            // filter user role that is not match to body role
            List<RoleRepresentation> filteredUserRole =  body.role()
                    .stream()
                    .flatMap(r -> listUserRoles
                            .stream()
                            .filter(ro -> !ro.getName().equals(r)))
                    .collect(Collectors.toList());

            // delete user role that is not match to the body role
            if(!filteredUserRole.isEmpty()) {
                realmResource()
                        .users()
                        .get(body.id())
                        .roles()
                        .clientLevel(fetchClientUUID())
                        .remove(filteredUserRole);
            }

            // create list of role from body role
            List<RoleRepresentation> resolvedRoles = body.role()
                    .stream()
                    .map(r -> realmResource()
                            .clients()
                            .get(fetchClientUUID())
                            .roles()
                            .get(r)
                            .toRepresentation())
                    .toList();

            //assign role to user
            realmResource()
                    .users()
                    .get(body.id())
                    .roles()
                    .clientLevel(fetchClientUUID())
                    .add(resolvedRoles);

            //update user
            userResource.update(existingUser);

        } catch (Exception e) {
            throw new CustomRunTimeException(e.getMessage());
        }
    }

    /**
     * Fetch all roles
     */
    public void fetchAllRoles() {
        realmResource()
                .roles()
                .list();
    }

    /**
     * Delete User
     */
    public void deleteUser(String id) {
         realmResource()
                 .users()
                 .get(id)
                 .remove();
    }
}
