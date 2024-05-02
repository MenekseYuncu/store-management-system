package org.menekseyuncu.storemanagementsystem.cart.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.cart.controller.request.CartItemUpdateRequest;
import org.menekseyuncu.storemanagementsystem.cart.controller.request.CartUpdateRequest;
import org.menekseyuncu.storemanagementsystem.cart.model.domain.Cart;
import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartEntity;
import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartItemEntity;
import org.menekseyuncu.storemanagementsystem.cart.model.mapper.CartEntityToDomainMapper;
import org.menekseyuncu.storemanagementsystem.cart.repository.CartItemRepository;
import org.menekseyuncu.storemanagementsystem.cart.repository.CartRepository;
import org.menekseyuncu.storemanagementsystem.cart.service.CartService;
import org.menekseyuncu.storemanagementsystem.common.exceptions.ResourceNotFoundException;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductEntityToDomainMapper;
import org.menekseyuncu.storemanagementsystem.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private static final CartEntityToDomainMapper cartEntityToDomainMapper = CartEntityToDomainMapper.INSTANCE;
    private static final ProductEntityToDomainMapper productEntityToDomain = ProductEntityToDomainMapper.INSTANCE;

    @Override
    public Cart getCart(Long customerId) {
        CartEntity cartEntity = cartRepository.findByCustomerId(customerId);

        if (cartEntity == null || cartEntity.isDeleted()) {
            throw new ResourceNotFoundException();
        }
        cartEntity.getCartItems().listIterator();

        return cartEntityToDomainMapper.map(cartEntity.toCart());
    }

    @Override
    public void updateCart(Long customerId, CartUpdateRequest updateRequest) {
        CartEntity cartEntity = cartRepository.findByCustomerId(customerId);

        if (cartEntity == null) {
            throw new ResourceNotFoundException();
        }

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItemUpdateRequest itemUpdateRequest : updateRequest.cartItems()) {
            Long productId = itemUpdateRequest.productId();
            Long quantity = itemUpdateRequest.quantity();


            CartItemEntity cartItem = cartItemRepository.findByCartIdAndProductId(cartEntity.getId(), productId);
            if (cartItem == null) {
                throw new ResourceNotFoundException();
            }

            ProductEntity product = cartItem.getProduct();

            if (quantity > product.getStock()) {
                throw new IllegalArgumentException("Available stock for product with ID: " + productId);
            }

            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);


            product.setStock(product.getStock() - quantity);
            productService.createProduct(productEntityToDomain.map(product));

            BigDecimal itemPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(itemPrice);
        }

        cartEntity.setTotalPrice(totalPrice);
        cartRepository.save(cartEntity);
    }


    @Override
    public void emptyCart(Long customerId) {
        CartEntity cartEntity = cartRepository.findByCustomerId(customerId);

        if (cartEntity != null) {

            cartItemRepository.deleteAll(cartEntity.getCartItems());
            cartEntity.getCartItems().clear();

            cartEntity.setTotalPrice(BigDecimal.ZERO);

            cartRepository.save(cartEntity);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
