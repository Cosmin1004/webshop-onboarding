package com.youngculture.webshop_onboarding.repository.impl;

import com.youngculture.webshop_onboarding.model.User;
import com.youngculture.webshop_onboarding.repository.UserRepository;
import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
