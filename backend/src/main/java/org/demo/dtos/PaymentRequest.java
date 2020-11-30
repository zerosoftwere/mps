package org.demo.dtos;

import io.smallrye.common.constraint.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull
    Long serviceId;

    @NotNull
    Integer quantity;
    
    @NotNull
    Integer amount;
}
