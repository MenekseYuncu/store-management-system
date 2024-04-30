package org.menekseyuncu.storemanagementsystem.orders.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;
import org.menekseyuncu.storemanagementsystem.orders.controller.request.OrderItemRequest;
import org.menekseyuncu.storemanagementsystem.orders.controller.request.OrderRequest;
import org.menekseyuncu.storemanagementsystem.orders.model.entity.OrderEntity;
import org.menekseyuncu.storemanagementsystem.orders.model.entity.OrderItemEntity;
import org.menekseyuncu.storemanagementsystem.orders.repository.OrderRepository;
import org.menekseyuncu.storemanagementsystem.orders.service.OrderService;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductEntityToDomainMapper;
import org.menekseyuncu.storemanagementsystem.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final ProductService productService;

    private static final ProductEntityToDomainMapper productEntityToDomain = ProductEntityToDomainMapper.INSTANCE;

    @Override
    public void placeOrder(Long customerId, OrderRequest orderRequest) {

        if (isStockAvailable(orderRequest)) {

            OrderEntity order = createOrder(customerId, orderRequest);

            BigDecimal totalPrice = calculateTotalPrice(order);

            order.setTotalPrice(totalPrice);

            orderRepository.save(order);
            updateStock(order);
        } else {
            throw new RuntimeException("Insufficient stock to place the order.");
        }
    }

    private boolean isStockAvailable(OrderRequest orderRequest) {
        for (OrderItemRequest orderItemRequest : orderRequest.orderItems()) {
            ProductEntity product = productService.getProduct(orderItemRequest.productId());
            if (product.getStock() < orderItemRequest.quantity()) {
                return false;
            }
        }
        return true;
    }

    private OrderEntity createOrder(Long customerId, OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();

        CustomerEntity customer = new CustomerEntity();
        customer.setId(customerId);
        order.setCustomer(customer);

        List<OrderItemEntity> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : orderRequest.orderItems()) {
            OrderItemEntity orderItem = new OrderItemEntity();
            ProductEntity product = productService.getProduct(orderItemRequest.productId());
            orderItem.setProduct(product);
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setQuantity(orderItemRequest.quantity());

            product.setStock(product.getStock() - orderItemRequest.quantity());
            productService.createProduct(productEntityToDomain.map(product));
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return order;
    }

    private BigDecimal calculateTotalPrice(OrderEntity order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemEntity orderItem : order.getOrderItems()) {
            totalPrice = totalPrice.add(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        }
        return totalPrice;
    }

    private void updateStock(OrderEntity order) {
        for (OrderItemEntity orderItem : order.getOrderItems()) {
            ProductEntity product = orderItem.getProduct();
            product.setStock(product.getStock() - orderItem.getQuantity());
            productService.createProduct(productEntityToDomain.map(product));
        }
    }
}
