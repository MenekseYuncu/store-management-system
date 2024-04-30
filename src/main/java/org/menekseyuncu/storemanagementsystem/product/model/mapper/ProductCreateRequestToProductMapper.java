package org.menekseyuncu.storemanagementsystem.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;

@Mapper
public interface ProductCreateRequestToProductMapper extends BaseMapper<ProductCreateRequest, Product> {

    ProductCreateRequestToProductMapper INSTANCE = Mappers.getMapper(ProductCreateRequestToProductMapper.class);

}
