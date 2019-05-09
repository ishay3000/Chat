package Dao;

public interface IHibernateDao<T> {
    /**
     * attempts to add the item to the database
     * @param item the item to be added
     * @return whether the item was added
     */
    boolean add(T item);

    /**
     * checks if the item exists in the database
     * @param item the item to check the existence for
     * @return whether the item exists in the database
     */
    boolean exists(T item);
}
