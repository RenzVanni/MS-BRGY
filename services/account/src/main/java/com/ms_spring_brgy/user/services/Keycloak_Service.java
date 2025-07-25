package com.ms_spring_brgy.user.services;

import com.ms_spring_brgy.user.constants.UserData;
import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import com.ms_spring_brgy.user.helper.Mapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@RequiredArgsConstructor
public class Keycloak_Service {
//    private final Mapper mapper;
    String realm = "auth";

//    @Value("${jwt.auth.realm-name}")
//    String envRealm;

    Keycloak keycloak = Keycloak.getInstance(
            "http://localhost:8080",
            "master",
            "admin",
            "admin",
            "admin-cli"
    );

    //fetch all users
    public List<Auth_Response_DTO> getUsers() {
        List<UserRepresentation> userRepresentation = keycloak
                .realm(realm)
                .users()
                .list();

        return Mapper.authResponseMapper(userRepresentation);
    }

    //create user
    public void createUser(UserData body) {
        Map<String, List<String>> attribute = new HashMap<>();
        attribute.put("resident_id", List.of(body.getResidentId().toString()));

        //create a user
        UserRepresentation user = new UserRepresentation();
        user.setUsername(body.getUsername());
        user.setEnabled(true);
        user.setEmail(body.getEmail());
        user.setAttributes(attribute);

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
            keycloak.realm(realm).users().create(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
