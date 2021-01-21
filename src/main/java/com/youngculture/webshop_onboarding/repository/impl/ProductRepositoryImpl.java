package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> findAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            products = session
                    .createQuery("FROM Product")
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            products = session
                    .createQuery("FROM Product P WHERE P.category.name = :category")
                    .setParameter("category", category)
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public Product findProductByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = null;
        try {
            product = (Product) session
                    .createQuery("FROM Product P WHERE P.name = :name")
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

}
