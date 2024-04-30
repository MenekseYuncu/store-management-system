package org.menekseyuncu.storemanagementsystem.product.controller.response;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        BigDecimal price,
        Long stock
) {
}
