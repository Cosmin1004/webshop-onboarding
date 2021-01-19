package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
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
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        List<Product> products = null;
        try {
            products = session
                    .createQuery("FROM Product P WHERE P.category.name = :category")
                    .setParameter("category", category)
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findProductByName(String name) {
        Product product = null;
        try {
            product = (Product) session
                    .createQuery("FROM Product P WHERE P.name = :name")
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return product;
    }

}
