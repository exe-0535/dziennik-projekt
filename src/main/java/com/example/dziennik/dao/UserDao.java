package com.example.dziennik.dao;

import com.example.dziennik.model.User;
import com.example.dziennik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public static List<String> getMails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<String> query = session.createQuery("SELECT u.email FROM User u WHERE u.role = :role", String.class);
            query.setParameter("role", User.Role.TEACHER);
            return query.getResultList();
        } catch (NoResultException ex) {
            System.err.println("Couldn't retireve the emails list");
            return null;
        }
    }

    public static Long getUserIdByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Long> query = session.createQuery("SELECT u.id FROM User u WHERE u.email = :email", Long.class);
            query.setParameter("email", email);
            // Retrieve the user_id or return null if not found
            List<Long> userIds = query.getResultList();
            return userIds.isEmpty() ? null : userIds.get(0);
        } catch (NoResultException ex) {
            System.err.println("Couldn't retireve the id");
            return null;
        }
    }

}
