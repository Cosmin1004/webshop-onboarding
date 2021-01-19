package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.OrderRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Status;

public class OrderRepositoryImpl implements OrderRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void saveOrder(Order order) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void updateOrderQuantity(Order order) {
        Transaction transaction = null;
        Order orderDb = findOrderByUserAndProduct(order.getUser(), order.getProduct());
        try {
            transaction = session.beginTransaction();
            String query = "UPDATE Orders SET quantity = quantity + 1 WHERE id = " + orderDb.getId();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void updateOrderStatus(Order order, Status status) {
        Transaction transaction = null;
        Order orderDb = findOrderByUserAndProduct(order.getUser(), order.getProduct());
        try {
            transaction = session.beginTransaction();
            String query = "UPDATE Orders SET status = :status WHERE id = " + orderDb.getId();
            session.createSQLQuery(query)
                    .setParameter("status", status)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public Order findOrderByUserAndProduct(User user, Product product) {
        Order order = null;
        try {
            if(user != null) {
                order = (Order) session
                        .createQuery("FROM Order O WHERE O.product.name = :name AND O.user.id = :id")
                        .setParameter("id", user.getId())
                        .setParameter("name", product.getName())
                        .uniqueResult();
            } else {
                order = (Order) session
                        .createQuery("FROM Order O WHERE O.product.name = :name AND O.user = null")
                        .setParameter("name", product.getName())
                        .uniqueResult();
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return order;
    }
}
