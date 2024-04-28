package org.menekseyuncu.storemanagementsystem.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.menekseyuncu.storemanagementsystem.common.exceptions.ResourceNotFoundException;
import org.menekseyuncu.storemanagementsystem.customer.controller.request.CustomerAddRequest;
import org.menekseyuncu.storemanagementsystem.customer.service.CustomerService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private final static String BASE_URL = "/api/v1/customer";

    @Test
    void givenAddCustomer_whenValidInput_thenReturnsSuccess() throws Exception {
        // Given
        CustomerAddRequest request = new CustomerAddRequest(
                "Test",
                "deneme@mail.com",
                "This is an address"
        );

        // When
        Mockito.doNothing().when(customerService).addCustomer(Mockito.any(CustomerAddRequest.class));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        Mockito.verify(customerService, Mockito.times(1)).addCustomer(request);
    }

    @Test
    void givenAddRequest_whenInvalidInput_thenReturnBadRequest() throws Exception {
        // Given
        CustomerAddRequest request = new CustomerAddRequest(
                null,
                null,
                null
        );

        // When
        Mockito.doThrow(new ResourceNotFoundException())
                .when(customerService).addCustomer(request);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(customerService);
    }
}