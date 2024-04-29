package org.menekseyuncu.storemanagementsystem.cart.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.cart.model.domain.Cart;
import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartEntity;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;

@Mapper
public interface CartEntityToDomainMapper extends BaseMapper<CartEntity, Cart> {

    CartEntityToDomainMapper INSTANCE = Mappers.getMapper(CartEntityToDomainMapper.class);
}
