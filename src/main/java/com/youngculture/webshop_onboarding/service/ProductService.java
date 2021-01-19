package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product getProductByName(String name);

}
