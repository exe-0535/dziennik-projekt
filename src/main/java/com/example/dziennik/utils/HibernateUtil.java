package com.example.dziennik.utils;

import com.example.dziennik.dao.LessonDao;
import com.example.dziennik.model.Lesson;
import com.example.dziennik.model.Unit;
import com.example.dziennik.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/school_log");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Unit.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Lesson.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                System.out.println("Session factory setup successful");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;

    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        LessonDao lessonDao = new LessonDao();

        List<Lesson> lessons = lessonDao.getLessonsForStudent();
        Collections.sort(lessons);

        for (Lesson lesson : lessons) {
            System.out.println("Lesson ID: " + lesson.getId());
            System.out.println("Unit ID: " + lesson.getUnit().getId());
            System.out.println("Subject: " + lesson.getSubject());
            System.out.println("Day: " + lesson.getDay());
            System.out.println("Class: " + lesson.getClassNumber());
            System.out.println("Lesson Number: " + lesson.getNr());
            System.out.println("Is Modified: " + lesson.getModified());
            // Add other parameters as needed
            System.out.println("-----------------------");
        }


        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Shutdown Hibernate
        shutdown();
    }
}
