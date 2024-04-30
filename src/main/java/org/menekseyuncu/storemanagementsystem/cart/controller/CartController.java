package org.menekseyuncu.storemanagementsystem.cart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.cart.controller.request.CartUpdateRequest;
import org.menekseyuncu.storemanagementsystem.cart.controller.response.CartResponse;
import org.menekseyuncu.storemanagementsystem.cart.model.domain.Cart;
import org.menekseyuncu.storemanagementsystem.cart.model.mapper.CartToCartResponseMapper;
import org.menekseyuncu.storemanagementsystem.cart.service.CartService;
import org.menekseyuncu.storemanagementsystem.common.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
class CartController {

    private final CartService cartService;
    private static final CartToCartResponseMapper cartToCartResponse = CartToCartResponseMapper.INSTANCE;

    @GetMapping("/{customerId}")
    public BaseResponse<CartResponse> getCart(
            @PathVariable @Positive Long customerId
    ) {
        Cart cart = cartService.getCart(customerId);
        CartResponse cartResponse = cartToCartResponse.map(cart);
        return BaseResponse.successOf(cartResponse);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<BaseResponse<Void>> updateCart(
            @PathVariable @Positive Long customerId,
            @Valid @RequestBody CartUpdateRequest updateRequest
    ) {
        cartService.updateCart(customerId, updateRequest);
        return ResponseEntity.ok(BaseResponse.SUCCESS);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<BaseResponse<Void>> emptyCart(
            @PathVariable @Positive Long customerId
    ) {
        cartService.emptyCart(customerId);
        return ResponseEntity.ok(BaseResponse.SUCCESS);
    }
}
