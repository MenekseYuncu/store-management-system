package org.menekseyuncu.storemanagementsystem.customer.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.customer.model.domain.Customer;
import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;

@Mapper
public interface CustomerEntityToDomainMapper extends BaseMapper<CustomerEntity, Customer> {

    CustomerEntityToDomainMapper INSTANCE = Mappers.getMapper(CustomerEntityToDomainMapper.class);
}
