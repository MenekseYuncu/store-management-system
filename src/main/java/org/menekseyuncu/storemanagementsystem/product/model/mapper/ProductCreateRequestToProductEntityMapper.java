package org.menekseyuncu.storemanagementsystem.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

@Mapper
public interface ProductCreateRequestToProductEntityMapper extends BaseMapper<ProductCreateRequest, ProductEntity> {

    ProductCreateRequestToProductEntityMapper INSTANCE = Mappers.getMapper(ProductCreateRequestToProductEntityMapper.class);

}
