package com.ms_spring_brgy.user.constants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {

    @NotNull(message = "Username required!")
    String username;

    @NotNull(message = "Resident ID required!")
    String email;

    String password;
    String confirmPassword;

    @NotNull(message = "Resident ID required!")
    Long residentId;

    @NotNull(message = "Account Role required!")
    String role;
}
