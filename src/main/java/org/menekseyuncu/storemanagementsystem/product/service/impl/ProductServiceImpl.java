package org.menekseyuncu.storemanagementsystem.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.common.exceptions.ResourceNotFoundException;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;
import org.menekseyuncu.storemanagementsystem.product.model.entity.ProductEntity;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductEntityToDomainMapper;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import org.menekseyuncu.storemanagementsystem.product.repository.ProductRepository;
import org.menekseyuncu.storemanagementsystem.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntity = ProductCreateRequestToProductEntityMapper.INSTANCE;
    private static final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntity = ProductUpdateRequestToProductEntityMapper.INSTANCE;
    private static final ProductEntityToDomainMapper productEntityToDomain = ProductEntityToDomainMapper.INSTANCE;


    @Override
    public Product getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return productEntityToDomain.map(productEntity);
    }

    @Override
    public void createProduct(ProductCreateRequest request) {

        ProductEntity product = productCreateRequestToProductEntity.map(request);

        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductUpdateRequest request) {

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        ProductEntity updateEntity = productUpdateRequestToProductEntity.map(request);

        productEntity.setName(updateEntity.getName());
        productEntity.setPrice(updateEntity.getPrice());
        productEntity.setStock(updateEntity.getStock());

        productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        product.softDelete();
        productRepository.save(product);
    }
}
