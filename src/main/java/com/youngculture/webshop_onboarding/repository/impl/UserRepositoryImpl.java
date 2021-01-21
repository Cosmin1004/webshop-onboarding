package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.UserRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            user = (User) session
                    .createQuery("FROM User U WHERE U.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
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
