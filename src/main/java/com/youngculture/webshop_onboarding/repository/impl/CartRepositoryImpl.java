package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.CartRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.postgresql.util.PSQLException;

import java.util.List;

public class CartRepositoryImpl implements CartRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Order> findUserCart(User user) {
        List<Order> orders = null;

        try {
            if (user != null) {
                orders = session
                        .createQuery("FROM Order O WHERE O.user.id = :id")
                        .setParameter("id", user.getId())
                        .list();
            } else {
                orders = session
                        .createQuery("FROM Order O WHERE O.user = null")
                        .list();
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

}
