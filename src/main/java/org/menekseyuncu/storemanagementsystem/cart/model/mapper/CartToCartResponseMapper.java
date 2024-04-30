package org.menekseyuncu.storemanagementsystem.cart.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.cart.controller.response.CartResponse;
import org.menekseyuncu.storemanagementsystem.cart.model.domain.Cart;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;

@Mapper
public interface CartToCartResponseMapper extends BaseMapper<Cart, CartResponse> {

    CartToCartResponseMapper INSTANCE = Mappers.getMapper(CartToCartResponseMapper.class);
}
