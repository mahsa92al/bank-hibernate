package ir.maktab.dao;

import ir.maktab.model.Client;
import ir.maktab.model.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public List<Transaction> findThreeLastTransactionsByCardNumber(long cardNumber) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        Query<Transaction> hql = session.createQuery("select t from Transaction t join t.account a where a.cardNumber=:cardNumber" +
                " order by t.transactionDate desc ");
        hql.setFirstResult(0);
        hql.setMaxResults(3);
        hql.setParameter("cardNumber", cardNumber);

        List<Transaction> transactions = hql.getResultList();
        transaction.commit();
        session.close();
        return transactions;
    }
}
