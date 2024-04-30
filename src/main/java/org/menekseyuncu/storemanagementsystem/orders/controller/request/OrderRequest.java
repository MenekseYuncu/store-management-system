package org.menekseyuncu.storemanagementsystem.orders.controller.request;

import java.util.List;

public record OrderRequest(
        List<OrderItemRequest> orderItems
) {
}
