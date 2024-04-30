package org.menekseyuncu.storemanagementsystem.cart.model.domain;

import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartEntity;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

public record CartItem(
        Long id,
        CartEntity cart,
        ProductEntity product,
        Long quantity
) {
}
