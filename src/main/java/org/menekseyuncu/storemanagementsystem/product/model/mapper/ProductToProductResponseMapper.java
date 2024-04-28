package org.menekseyuncu.storemanagementsystem.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.product.controller.response.ProductResponse;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;

@Mapper
public interface ProductToProductResponseMapper extends BaseMapper<Product, ProductResponse> {

    ProductToProductResponseMapper INSTANCE = Mappers.getMapper(ProductToProductResponseMapper.class);
}
