package org.demo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class VendorCreateRequest {

    @NotBlank
    @NotEmpty
    private String name;
}