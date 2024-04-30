package org.menekseyuncu.storemanagementsystem.cart.controller.request;

import jakarta.validation.constraints.NotNull;

public record CartItemUpdateRequest(

        @NotNull
        Long productId,
        @NotNull
        Long quantity
) {
}
