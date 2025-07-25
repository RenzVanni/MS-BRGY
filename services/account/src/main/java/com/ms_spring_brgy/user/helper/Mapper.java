package com.ms_spring_brgy.user.helper;

import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Map;

public class Mapper {
    public static List<Auth_Response_DTO> authResponseMapper(List<UserRepresentation> body) {
        return body
                .stream()
                .map(auth -> {
                    Map<String, List<String>> getAttribute = auth.getAttributes();
                    String attribute = null;

                    if(getAttribute != null && !getAttribute.get("resident_id").isEmpty()) {
                        attribute = getAttribute.get("resident_id").get(0);
                    }

                    String email = null;
                    if(!auth.getEmail().isEmpty()) {
                        email = auth.getEmail();
                    }
                    return Auth_Response_DTO.builder()
                        .id(auth.getId())
                        .username(auth.getUsername())
                        .email(email)
                        .resident_id(attribute)
                        .build();
                })
                .toList();
    }
}
