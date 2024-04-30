package org.menekseyuncu.storemanagementsystem.cart.service;

import org.menekseyuncu.storemanagementsystem.cart.controller.request.CartUpdateRequest;
import org.menekseyuncu.storemanagementsystem.cart.model.domain.Cart;

public interface CartService {

    Cart getCart(Long customerId);

    void updateCart(Long customerId, CartUpdateRequest updateRequest);

    void emptyCart(Long customerId);
}
