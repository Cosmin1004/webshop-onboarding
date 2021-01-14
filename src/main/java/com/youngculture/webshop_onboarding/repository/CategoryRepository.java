package com.youngculture.webshop_onboarding.repository;

import com.youngculture.webshop_onboarding.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAllCategories();

}
