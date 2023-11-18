package com.example.dziennik.dao;

import com.example.dziennik.helpers.CurrentUser;
import com.example.dziennik.model.Lesson;
import com.example.dziennik.model.Unit;
import com.example.dziennik.model.User;
import com.example.dziennik.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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


    public List<Lesson> getLessonsForStudent() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Unit> query = session.createQuery(
                    "SELECT u FROM Unit u " +
                            "JOIN u.users user " +
                            "WHERE user.id = :user_id",
                    Unit.class);
            query.setParameter("user_id", CurrentUser.getCurrentUser().getId());

            long id_u = query.getSingleResult().getId();
            System.out.println(id_u);

            TypedQuery<Lesson> q = session.createQuery("SELECT l FROM Lesson l WHERE unit_id = :unit_id", Lesson.class);
            q.setParameter("unit_id", id_u);

            return q.getResultList();
        } catch (NoResultException ex) {
            System.err.println("Lessons not found");
            return null;
        }
    }

    public void updateLesson(long nr, long new_user_id, long old_user_id, String day, String subject, long classNumber) {
        Transaction ts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ts = session.beginTransaction();

            session.createQuery("UPDATE Lesson l SET l.subject = :subject, l.classNumber = :classNumber, l.isModified = 1, l.user.id = :new_user_id WHERE day = :day AND nr = :nr AND user.id = :old_user_id")
                    .setParameter("nr", nr)
                    .setParameter("old_user_id", old_user_id)
                    .setParameter("new_user_id", new_user_id)
                    .setParameter("day", day)
                    .setParameter("subject", subject)
                    .setParameter("classNumber", classNumber)
                    .executeUpdate();

            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
                System.out.println("Transaction rolled back.");
            }
            e.printStackTrace();
        }
    }
}
