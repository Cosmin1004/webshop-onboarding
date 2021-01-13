package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    List<Product> findProductsByCategory(Category category);

}
