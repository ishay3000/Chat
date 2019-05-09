package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static void buildSessionFactory() {
        try {
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
            sessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        synchronized (Class.class) {
            if (sessionFactory == null){
                buildSessionFactory();
            }
        }
        return sessionFactory;
    }
}
