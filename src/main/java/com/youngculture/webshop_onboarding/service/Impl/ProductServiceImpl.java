package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }
}
