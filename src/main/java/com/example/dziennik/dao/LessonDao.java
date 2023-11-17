package com.example.dziennik.dao;

import com.example.dziennik.helpers.CurrentUser;
import com.example.dziennik.model.Lesson;
import com.example.dziennik.model.User;
import com.example.dziennik.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LessonDao {
    public List<Lesson> getLessonsForTeacher() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Lesson> query = session.createQuery("SELECT l FROM Lesson l WHERE user_id = :user_id", Lesson.class);
            query.setParameter("user_id", CurrentUser.getCurrentUser().getId());
            return query.getResultList();
        } catch (NoResultException ex) {
            System.err.println("Lessons not found");
            return null;
        }
    }

    public List<Lesson> getLessonsForTeacher(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Lesson> query = session.createQuery("SELECT l FROM Lesson l WHERE user_id = :user_id", Lesson.class);
            query.setParameter("user_id", id);
            return query.getResultList();
        } catch (NoResultException ex) {
            System.err.println("Lessons not found");
            return null;
        }
    }

}
