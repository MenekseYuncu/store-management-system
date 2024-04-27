package org.menekseyuncu.storemanagementsystem.customer.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;
import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;
import org.menekseyuncu.storemanagementsystem.customer.model.mapper.CustomerAddRequestToCustomerEntityMapper;
import org.menekseyuncu.storemanagementsystem.customer.repository.CustomerRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private static final CustomerAddRequestToCustomerEntityMapper customerAddRequestToCustomerEntity = CustomerAddRequestToCustomerEntityMapper.INSTANCE;

    @Test
    void givenAddCustomer_whenSaveFails_thenThrowException() {
        // Given
        CustomerAddRequest customerAddRequest = new CustomerAddRequest(
                null,
                "test@deneme.com",
                null
        );

        // When
        Mockito.when(customerRepository.save(ArgumentMatchers.any(CustomerEntity.class)))
                .thenThrow(IllegalArgumentException.class);

        // Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> customerService.addCustomer(customerAddRequest));

        Mockito.verify(customerRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(CustomerEntity.class));
    }

    @Test
    void givenAddCustomer_whenEmailAlreadyExists_thenThrowException() {
        // Given
        CustomerAddRequest customerAddRequest = new CustomerAddRequest(
                "Customer",
                "deneme@mail.com",
                "This is an address "
        );

        // When
        Mockito.when(customerRepository.existsByEmail(customerAddRequest.email()))
                .thenReturn(true);

        // Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> customerService.addCustomer(customerAddRequest));

        // Verify
        Mockito.verify(customerRepository, Mockito.times(1))
                .existsByEmail(customerAddRequest.email());

        Mockito.verify(customerRepository, Mockito.never())
                .save(ArgumentMatchers.any(CustomerEntity.class));
    }

    @Test
    void givenAddCustomer_thenSaveCustomerEntity() {
        // Given
        CustomerAddRequest customerAddRequest = new CustomerAddRequest(
                "Test",
                "testmail@mail.com",
                "This is an address"
        );
        CustomerEntity customerEntity = customerAddRequestToCustomerEntity.map(customerAddRequest);

        // When
        Mockito.when(customerRepository.save(ArgumentMatchers.any(CustomerEntity.class)))
                .thenReturn(customerEntity);

        customerService.addCustomer(customerAddRequest);

        // Then
        Mockito.verify(customerRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(CustomerEntity.class));
    }
}