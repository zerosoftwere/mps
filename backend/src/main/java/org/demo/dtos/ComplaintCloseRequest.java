package org.demo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ComplaintCloseRequest {
    @NotBlank
    @NotEmpty
    private String resolution;
}
