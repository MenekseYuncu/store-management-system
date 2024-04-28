package org.menekseyuncu.storemanagementsystem.product.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateRequest(

        @NotNull
        @Size(min = 2, max = 300)
        String name,
        @NotNull
        BigDecimal price,
        @NotNull
        @Positive
        Long stock
) {
}
