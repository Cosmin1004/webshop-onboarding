package com.youngculture.webshop_onboarding.service.Impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.repository.CategoryRepository;
import com.youngculture.webshop_onboarding.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        List<Category> categories = getAllCategories();

        for(Category category : categories) {
            categoryNames.add(category.getName());
        }

        return categoryNames;
    }

}
