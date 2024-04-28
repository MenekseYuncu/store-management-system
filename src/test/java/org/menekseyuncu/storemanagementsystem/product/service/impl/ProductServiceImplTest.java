package org.menekseyuncu.storemanagementsystem.product.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.menekseyuncu.storemanagementsystem.common.exceptions.ResourceNotFoundException;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductEntityToDomainMapper;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import org.menekseyuncu.storemanagementsystem.product.repository.ProductRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntity = ProductCreateRequestToProductEntityMapper.INSTANCE;
    private static final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntity = ProductUpdateRequestToProductEntityMapper.INSTANCE;
    private static final ProductEntityToDomainMapper productEntityToDomain = ProductEntityToDomainMapper.INSTANCE;


    @Test
    void givenGetProductById_whenProductExists_thenReturnProduct() {
        //Given
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setName("Test");
        productEntity.setPrice(BigDecimal.valueOf(100));
        productEntity.setStock(10L);
        productEntity.setCreatedAt(LocalDateTime.now());

        //When
        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.of(productEntity));

        Product mockProduct = productEntityToDomain.map(productEntity);

        //Then
        Product resultProduct = productService.getProduct(productId);

        Assertions.assertNotNull(resultProduct);
        Assertions.assertEquals(mockProduct.getId(), resultProduct.getId());
        Assertions.assertEquals(mockProduct.getName(), resultProduct.getName());
        Assertions.assertEquals(mockProduct.getPrice(), resultProduct.getPrice());
        Assertions.assertEquals(mockProduct.getStock(), resultProduct.getStock());
        Assertions.assertEquals(mockProduct.getCreatedAt(), resultProduct.getCreatedAt());
    }

    @Test
    void givenGetProductById_whenProductDoesNotExist_thenThrowResourceNotFoundException() {
        //Given
        Long productId = 1L;

        //When
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        //Then
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.getProduct(productId));
    }

    @Test
    void givenCreateProduct_whenSaveFails_thenThrowException() {
        // Given
        ProductCreateRequest request = new ProductCreateRequest(
                null,
                null,
                null
        );

        // When
        Mockito.when(productRepository.save(ArgumentMatchers.any(ProductEntity.class)))
                .thenThrow(IllegalArgumentException.class);

        // Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> productService.createProduct(request));

        Mockito.verify(productRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ProductEntity.class));
    }

    @Test
    void givenCreateProduct_thenSaveProductEntity() {
        // Given
        ProductCreateRequest request = new ProductCreateRequest(
                "Test",
                BigDecimal.valueOf(100),
                14L
        );
        ProductEntity productEntity = productCreateRequestToProductEntity.map(request);

        // When
        Mockito.when(productRepository.save(ArgumentMatchers.any(ProductEntity.class)))
                .thenReturn(productEntity);

        productService.createProduct(request);

        // Then
        Mockito.verify(productRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ProductEntity.class));
    }

    @Test
    void givenUpdateProduct_whenProductExists_thenUpdateProductEntity() {
        // Given
        Long productId = 1L;
        ProductUpdateRequest request = new ProductUpdateRequest(
                "UpdateTest",
                BigDecimal.valueOf(100),
                14L
        );
        ProductEntity productEntity = productUpdateRequestToProductEntity.map(request);

        // When
        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.of(productEntity));

        productService.updateProduct(productId, request);

        // Then
        Mockito.verify(productRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ProductEntity.class));
    }

    @Test
    void givenUpdateProduct_whenProductIdDoesNotExists_thenThrowResourceNotFoundException() {
        // Given
        Long productId = 1L;
        ProductUpdateRequest request = new ProductUpdateRequest(
                "UpdateTest",
                BigDecimal.valueOf(100),
                14L
        );

        // When
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        //Then
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.updateProduct(productId, request));
    }

    @Test
    void givenDeleteProduct_whenProductExists_thenUpdatedProductEntity() {
        // Given
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();

        // When
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        productService.deleteProduct(productId);

        // Then
        Mockito.verify(productRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ProductEntity.class));
    }

    @Test
    void givenSoftDeleteProduct_whenProductIdDoesNotExists_thenThrowResourceNotFoundException() {
        //Given
        Long productId = 1L;

        //When
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        //Then
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.deleteProduct(productId));
    }
}