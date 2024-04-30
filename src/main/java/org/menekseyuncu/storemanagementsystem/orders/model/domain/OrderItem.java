package org.menekseyuncu.storemanagementsystem.orders.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.menekseyuncu.storemanagementsystem.orders.model.entity.OrderEntity;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Long id;
    private OrderEntity order;
    private ProductEntity product;
    private BigDecimal unitPrice;
    private Long quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
