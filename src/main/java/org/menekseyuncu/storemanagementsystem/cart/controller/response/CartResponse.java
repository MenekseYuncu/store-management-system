package org.menekseyuncu.storemanagementsystem.cart.controller.response;

import org.menekseyuncu.storemanagementsystem.customer.model.domain.Customer;

import java.math.BigDecimal;

public record CartResponse(

        Customer customer,
        BigDecimal totalPrice
) {
}
