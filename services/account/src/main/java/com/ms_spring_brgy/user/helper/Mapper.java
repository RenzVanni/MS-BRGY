package com.ms_spring_brgy.user.helper;

import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Component
public class Mapper {
    @Value("${jwt.auth.resident-id}")
    private String residentId;

    public Auth_Response_DTO mapUserRepresentation(UserRepresentation user, RealmResource keycloak, String clientUUID) {
        Long convertResidentId = Long.valueOf(user.getAttributes().get("resident_id").get(0));

        //fetch roles
        List<RoleRepresentation> roleRepresentations = keycloak
                .users()
                .get(user.getId())
                .roles()
                .clientLevel(clientUUID)
                .listEffective();

        //map roles and fetch only the name
        List<String> roles = roleRepresentations
                .stream()
                .map(RoleRepresentation::getName)
                .toList();

        return Auth_Response_DTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .resident_id(convertResidentId)
                .role(roles)
                .build();
    }

    public List<Auth_Response_DTO> authResponseMapper(List<UserRepresentation> body, RealmResource keycloak, String clientUUID) {
        return body
                .stream()
                .map(auth -> {
                    Map<String, List<String>> getAttribute = auth.getAttributes();
                    Long resident_id = null;

                    //verify if attribute is not null and attribute named resident_id is not empty
                    //then assign the attribute
                    if(getAttribute != null && Objects.nonNull(getAttribute.get(residentId))) {
                        String attribute = getAttribute.get(residentId).get(0);

                        //convert string to Long
                        resident_id = Long.valueOf(attribute);
                    }

                    //fetch roles
                    List<RoleRepresentation> roleRepresentations = keycloak
                            .users()
                            .get(auth.getId())
                            .roles()
                            .clientLevel(clientUUID)
                            .listEffective();

                    //map roles and fetch only the name
                    List<String> roles = roleRepresentations
                            .stream()
                            .map(RoleRepresentation::getName)
                            .toList();

                    String email = null;
                    if(Objects.nonNull(auth.getEmail())) {
                        email = auth.getEmail();
                    }
                    return Auth_Response_DTO.builder()
                        .id(auth.getId())
                        .username(auth.getUsername())
                        .email(email)
                        .resident_id(resident_id)
                        .role(roles)
                        .build();
                })
                .toList();
    }
}
