package org.demo.domain;

import lombok.Data;

@Data
public class CheckoutSummary {
    String name;
    String vendor;
    Integer amount;
    Integer quantity;
    Boolean isFixed;
}
