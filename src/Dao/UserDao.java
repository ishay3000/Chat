package Dao;

import SqlMappings.MySqlUsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

public class UserDao implements IHibernateDao<MySqlUsersEntity> {
    private static UserDao OUR_INSTANCE = new UserDao();

    public static synchronized UserDao getOurInstance(){
        return OUR_INSTANCE;
    }

    private UserDao(){
    }

    @Override
    public boolean add(MySqlUsersEntity item) {
        boolean isActionOk = true;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                isActionOk = false;
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return isActionOk;
    }

    @Override
    public boolean exists(MySqlUsersEntity item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean doesExist = false;

        try {
            Query query = session.createQuery("select count (*) from MySqlUsersEntity user " +
                    "where user.username = :uname");
            query.setParameter("uname", item.getUsername());
            Long res = (Long) query.uniqueResult();
            doesExist = res > 0;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            session.close();
        }

        return doesExist;
    }
}
