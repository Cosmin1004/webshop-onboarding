package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = null;
        try {
            products = session
                    .createQuery("FROM Product")
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        List<Product> products = null;
        try {
            products = session
                    .createQuery("FROM Product P WHERE P.category.id = :category_id")
                    .setParameter("category_id", category.getId())
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

}
