package org.menekseyuncu.storemanagementsystem.orders.controller;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.common.responses.BaseResponse;
import org.menekseyuncu.storemanagementsystem.orders.controller.request.OrderRequest;
import org.menekseyuncu.storemanagementsystem.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @PostMapping("/place/{customerId}")
    public ResponseEntity<BaseResponse<String>> placeOrder(
            @PathVariable Long customerId,
            @RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(customerId, orderRequest);
        return ResponseEntity.ok(BaseResponse.successOf("Order placed successfully."));
    }
}
