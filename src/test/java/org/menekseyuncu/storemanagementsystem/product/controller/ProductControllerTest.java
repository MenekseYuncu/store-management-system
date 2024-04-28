package org.menekseyuncu.storemanagementsystem.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.menekseyuncu.storemanagementsystem.common.exceptions.ResourceNotFoundException;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;
import org.menekseyuncu.storemanagementsystem.product.service.ProductService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    private final static String BASE_URL = "/api/v1/product";

    @Test
    void givenGetProductById_whenValidInput_thenReturnSuccess() throws Exception {
        // Given
        Long productId = 1L;

        // When
        Product product = Product.builder()
                .id(productId)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .stock(20L)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deletedAt(null).build();

        Mockito.when(productService.getProduct(productId)).thenReturn(product);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"));

        // Verify
        Mockito.verify(productService, Mockito.times(1)).getProduct(productId);
    }


    @Test
    void givenGetCategoryById_whenInvalidCategoryId_thenReturnNotFound() throws Exception {
        // Given
        Long productId = 999L;

        // When
        Mockito.when(productService.getProduct(productId))
                .thenThrow(new ResourceNotFoundException());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verify(productService, Mockito.times(1)).getProduct(productId);
    }

    @Test
    void givenCreateProduct_whenValidInput_thenReturnSuccess() throws Exception {
        // Given
        ProductCreateRequest request = new ProductCreateRequest(
                "Test",
                BigDecimal.valueOf(1000),
                10L
        );

        // When
        Mockito.doNothing().when(productService).createProduct(Mockito.any(ProductCreateRequest.class));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        Mockito.verify(productService, Mockito.times(1)).createProduct(request);
    }

    @Test
    void givenCreateProduct_whenInvalidInput_thenReturnBadRequest() throws Exception {
        // Given
        ProductCreateRequest request = new ProductCreateRequest("Test", null, null);

        // When
        Mockito.doThrow(new ResourceNotFoundException()).when(productService).createProduct(request);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(productService);
    }

    @Test
    void givenUpdateProduct_whenValidInput_thenReturnSuccess() throws Exception {
        //Given
        Long productId = 1L;

        // When
        ProductUpdateRequest request = new ProductUpdateRequest(
                "UpdateTest",
                BigDecimal.valueOf(1000),
                15L
        );

        Mockito.doNothing().when(productService).updateProduct(productId, request);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));

        // Verify
        Mockito.verify(productService).updateProduct(productId, request);
    }

    @Test
    void givenUpdateCategory_whenInvalidInput_thenReturnBadRequest() throws Exception {
        // Given
        Long productId = 1L;

        // When
        ProductUpdateRequest request = new ProductUpdateRequest(
                null,
                null,
                null
        );
        //When
        Mockito.doThrow(new ResourceNotFoundException()).when(productService)
                .updateProduct(productId, request);

        //Then
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify
        Mockito.verifyNoInteractions(productService);
    }

    @Test
    void givenDeleteProduct_thenReturnSuccess() throws Exception {
        // Given
        Long productId = 1L;

        // When
        Mockito.doNothing().when(productService).deleteProduct(productId);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true));

        // Verify
        Mockito.verify(productService).deleteProduct(productId);
    }

    @Test
    void givenDeleteProduct_whenInvalidId_thenReturnNotFound() throws Exception {
        // Given
        Long productId = 100L;

        // When
        Mockito.doThrow(ResourceNotFoundException.class)
                .when(productService)
                .deleteProduct(productId);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify
        Mockito.verify(productService).deleteProduct(productId);
    }
}