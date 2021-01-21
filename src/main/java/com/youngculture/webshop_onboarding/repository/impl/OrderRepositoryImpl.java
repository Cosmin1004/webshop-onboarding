package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.Order;
import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.model.Status;
import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.OrderRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order findOrderByUserAndProduct(User user, Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order = null;
        try {
            order = (Order) session
                    .createQuery("FROM Order O WHERE O.product.name = :name AND O.user.id = :id AND O.status = 0")
                    .setParameter("id", user.getId())
                    .setParameter("name", product.getName())
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return order;
    }

    //all orders that are on status sent, confirmed or finished
    @Override
    public List<Order> findOrdersByUserAndStatus(User user) {
        List<Order> orders = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            orders = session.createQuery("FROM Order O WHERE O.user.id = :id and (O.status = 1 or O.status = 2 or O.status = 3)")
                    .setParameter("id", user.getId())
                    .list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

    @Override
    public void saveOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        } finally {
            session.close();
        }
    }

    @Override
    public void updateOrderQuantity(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Order orderDb = findOrderByUserAndProduct(order.getUser(), order.getProduct());
        try {
            transaction = session.beginTransaction();
            String query = "UPDATE Orders SET quantity = quantity + 1 WHERE id = "
                    + orderDb.getId() + " AND status = 0";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateOrderStatus(User user, Status currentStatus, Status newStatus) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String query = "UPDATE Orders SET status = " + newStatus.ordinal() +
                    " WHERE status = " + currentStatus.ordinal() +
                    " AND user_id = " + user.getId();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateOrderReference(User user, Long reference) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String query = "UPDATE Orders SET reference = " + reference +
                    " WHERE status = 0 AND user_id = " + user.getId();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteOrder(User user, Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Order orderDb = findOrderByUserAndProduct(user, product);
        try {
            transaction = session.beginTransaction();
            String query = "DELETE FROM Orders WHERE id = " + orderDb.getId();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
