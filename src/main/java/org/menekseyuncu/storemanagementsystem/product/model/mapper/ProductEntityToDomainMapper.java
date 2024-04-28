package org.menekseyuncu.storemanagementsystem.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;

@Mapper
public interface ProductEntityToDomainMapper extends BaseMapper<ProductEntity, Product> {

    ProductEntityToDomainMapper INSTANCE = Mappers.getMapper(ProductEntityToDomainMapper.class);

}
