package org.menekseyuncu.storemanagementsystem.product.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        String name,
        BigDecimal price,
        Long stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
