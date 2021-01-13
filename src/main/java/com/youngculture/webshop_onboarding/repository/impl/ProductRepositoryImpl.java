package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Category;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Product");
            products = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        List<Product> products = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Product P WHERE P.category.id = :category_id");
            query.setParameter("category_id", category.getId());
            products = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

}
