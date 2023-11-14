package com.example.dziennik.dao;

import com.example.dziennik.model.User;
import com.example.dziennik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDao {

    public User getConnectedUser(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<User> query = session.createQuery("SELECT u FROM User u WHERE email = :email AND password = :pass", User.class);
            query.setParameter("email", email);
            query.setParameter("pass", password);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("User not found");
            return null;
        }
    }

    public User getUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
