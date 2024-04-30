package org.menekseyuncu.storemanagementsystem.product.service;

import org.menekseyuncu.storemanagementsystem.product.controller.request.ProductUpdateRequest;
import org.menekseyuncu.storemanagementsystem.product.model.domain.Product;

public interface ProductService {

    Product getProduct(Long id);

    void createProduct(Product product);

    void updateProduct(Long id, ProductUpdateRequest request);

    void deleteProduct(Long id);
}
