package org.menekseyuncu.storemanagementsystem.orders.controller.request;

public record OrderItemRequest(
        Long productId,
        Long quantity
) {
}
