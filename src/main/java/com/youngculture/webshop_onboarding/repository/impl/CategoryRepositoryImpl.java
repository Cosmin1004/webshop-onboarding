package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.repository.CategoryRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = null;
        try {
            categories= session
                    .createQuery("from Category")
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categories;
    }

}
