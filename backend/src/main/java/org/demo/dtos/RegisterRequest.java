package org.demo.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegisterRequest {
    @NotEmpty
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String phone;

    @NotEmpty
    @NotBlank
    private String password;
}
