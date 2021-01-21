package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.repository.CategoryRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public List<Category> findAllCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categories = null;
        try {
            categories = session
                    .createQuery("FROM Category")
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return categories;

    }

}
