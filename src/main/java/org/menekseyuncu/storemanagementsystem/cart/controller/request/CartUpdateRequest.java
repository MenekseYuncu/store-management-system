package org.menekseyuncu.storemanagementsystem.cart.controller.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartUpdateRequest(
        @NotNull
        List<CartItemUpdateRequest> cartItems
) {
}
