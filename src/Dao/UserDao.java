package Dao;

import SqlMappings.MySqlUsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class UserDao implements IHibernateDao<MySqlUsersEntity> {
    public static UserDao OUR_INSTANCE = new UserDao();

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

        return false;
    }
}
