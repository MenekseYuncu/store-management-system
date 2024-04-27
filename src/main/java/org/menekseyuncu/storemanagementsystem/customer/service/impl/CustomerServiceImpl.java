package org.menekseyuncu.storemanagementsystem.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;
import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;
import org.menekseyuncu.storemanagementsystem.customer.model.mapper.CustomerAddRequestToCustomerEntityMapper;
import org.menekseyuncu.storemanagementsystem.customer.repository.CustomerRepository;
import org.menekseyuncu.storemanagementsystem.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private static final CustomerAddRequestToCustomerEntityMapper requestToCustomerEntityMapper = CustomerAddRequestToCustomerEntityMapper.INSTANCE;

    @Override
    public void addCustomer(CustomerAddRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("The email address is already in use.");
        }

        CustomerEntity customerEntity = requestToCustomerEntityMapper.map(request);
        customerRepository.save(customerEntity);
    }
}
