package org.demo.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserCreateRequest {
    @Email
    @NotEmpty
    String email;

    @NotBlank
    @NotEmpty
    String password;

    @NotBlank
    @NotEmpty
    String role;
}
