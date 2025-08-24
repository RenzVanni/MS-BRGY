package com.ms_spring_brgy.user.services;

import com.ms_spring_brgy.user.constants.UserData;
import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import com.ms_spring_brgy.user.dto.LoginRequestDTO;
import com.ms_spring_brgy.user.helper.Mapper;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
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

@Service
@RequiredArgsConstructor
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

    //fetch access token
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

//        try {
            return response.getBody().get("access_token").toString();
//        } catch (NullPointerException e) {
//            throw new NullPointerException("Empty Token");
//        }
    }

    //fetch keycloak realm
    private RealmResource realmResource() {
        return keycloak.realm(realmName);
    }

    //fetch client UUID
    private String fetchClientUUID() {
        return realmResource()
                .clients()
                .findByClientId(clientId)
                .get(0)
                .getId();
    }

    //fetch all users
    public List<Auth_Response_DTO> getUsers() {
        List<UserRepresentation> userRepresentation = realmResource()
                .users()
                .list();

        return mapper.authResponseMapper(userRepresentation, realmResource(), fetchClientUUID());
    }

    //create user
    public void createUser(UserData body) {
        Map<String, List<String>> attribute = new HashMap<>();
        attribute.put("resident_id", List.of(body.getResidentId().toString()));

        //create user
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

        //send request
        try {

            //convert Long resident id to String
            String convertResidentId = String.valueOf(body.getResidentId());

            //fetch all users
            List<UserRepresentation> fetchUsers = realmResource()
                    .users()
                    .list();

            //map all users to verify if resident id already existed
            //if existed throw an error
            for (UserRepresentation existingUser : fetchUsers) {
                Map<String, List<String>> existingAttribute = existingUser.getAttributes();
                if(existingAttribute.get(residentId).contains(convertResidentId)){
                    throw new RuntimeException("Resident ID already exists!");
                }
            }

            //create user
            Response response = realmResource()
                    .users()
                    .create(user);

            //throw error if user already exist
            if(response.getStatus() == 409) {
                throw new RuntimeException("User already exist");
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


            //client role list
            List<RoleRepresentation> clientRoles = realmResource()
                    .clients()
                    .get(fetchClientUUID())
                    .roles()
                    .list();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //fetch all roles
    public void fetchAllRoles() {
        realmResource().roles().list();
    }
}
