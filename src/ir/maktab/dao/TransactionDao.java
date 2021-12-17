package ir.maktab.dao;

import ir.maktab.model.Transaction;
import org.hibernate.Session;

/**
 * @author Mahsa Alikhani m-58
 */
public class TransactionDao extends BaseDao{

    public static void saveNewTransaction(Transaction clientTransaction) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        session.save(clientTransaction);
        transaction.commit();
        session.close();
    }
}
