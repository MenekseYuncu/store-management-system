package org.menekseyuncu.storemanagementsystem.product.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends ProductEntity {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
