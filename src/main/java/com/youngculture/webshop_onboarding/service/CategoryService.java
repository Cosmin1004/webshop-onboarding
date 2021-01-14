package com.youngculture.webshop_onboarding.service;

import com.youngculture.webshop_onboarding.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    List<String> getAllCategoryNames();

}
