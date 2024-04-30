package org.menekseyuncu.storemanagementsystem.orders.service;

import org.menekseyuncu.storemanagementsystem.orders.controller.request.OrderRequest;

public interface OrderService {

    void placeOrder(Long customerId, OrderRequest orderRequest);
}
