package org.menekseyuncu.storemanagementsystem.customer.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.storemanagementsystem.common.model.BaseMapper;
import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;
import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;

@Mapper
public interface CustomerAddRequestToCustomerEntityMapper extends BaseMapper<CustomerAddRequest, CustomerEntity> {
    CustomerAddRequestToCustomerEntityMapper INSTANCE = Mappers.getMapper(CustomerAddRequestToCustomerEntityMapper.class);
}
