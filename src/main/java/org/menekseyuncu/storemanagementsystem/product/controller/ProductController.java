package org.menekseyuncu.storemanagementsystem.product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.menekseyuncu.storemanagementsystem.common.responses.BaseResponse;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductCreateRequest;
import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.controller.response.ProductResponse;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductCreateRequestToProductMapper;
import org.menekseyuncu.storemanagementsystem.product.model.mapper.ProductToProductResponseMapper;
import org.menekseyuncu.storemanagementsystem.product.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
class ProductController {

    private final ProductService productService;
    private static final ProductToProductResponseMapper productToResponseMapper = ProductToProductResponseMapper.INSTANCE;
    private static final ProductCreateRequestToProductMapper productToRequestMapper = ProductCreateRequestToProductMapper.INSTANCE;

    @GetMapping("/{id}")
    public BaseResponse<ProductResponse> getProduct(
            @PathVariable @Positive Long id
    ) {
        Product product = productService.getProduct(id);
        ProductResponse response = productToResponseMapper.map(product);
        return BaseResponse.successOf(response);
    }

    @PostMapping
    public BaseResponse<Void> createProduct(
            @Valid @RequestBody ProductCreateRequest productCreateRequest
    ) {
        Product product = productToRequestMapper.map(productCreateRequest);
        productService.createProduct(product);
        return BaseResponse.SUCCESS;
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateProduct(
            @PathVariable @Positive Long id,
            @Valid @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        productService.updateProduct(id, productUpdateRequest);
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteProduct(
            @PathVariable @Positive Long id
    ) {
        productService.deleteProduct(id);
        return BaseResponse.SUCCESS;
    }
}
