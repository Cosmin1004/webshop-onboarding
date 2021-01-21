package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.CartRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CartRepositoryImpl implements CartRepository {

    @Override
    public List<Order> findUserCart(User user) {
        List<Order> orders = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            orders = session.createQuery("FROM Order O WHERE O.user.id = :id and O.status = 0")
                    .setParameter("id", user.getId())
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

}
