package com.ms_spring_brgy.user.constants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserData {
    String username;
    String email;
    String password;
    String confirmPassword;
    Long residentId;
}
