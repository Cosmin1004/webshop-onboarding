package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

}
