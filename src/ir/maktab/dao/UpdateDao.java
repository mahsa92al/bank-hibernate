package ir.maktab.dao;

import ir.maktab.model.Updates;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateDao extends BaseDao{
    public void saveNewUpdate(Updates type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(type);
        transaction.commit();
        session.close();
    }
}
