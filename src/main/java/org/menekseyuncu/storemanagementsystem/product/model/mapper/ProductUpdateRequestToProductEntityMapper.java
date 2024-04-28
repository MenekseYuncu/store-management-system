package org.menekseyuncu.storemanagementsystem.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {

    ProductUpdateRequestToProductEntityMapper INSTANCE = Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
}
