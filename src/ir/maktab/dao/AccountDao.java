package ir.maktab.dao;

import ir.maktab.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author Mahsa Alikhani m-58
 */
public class AccountDao extends BaseDao{

    public void saveNewAccount(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();
    }

    public Account findAccountByCardNumber(long cardNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Account> hql = session.createQuery("from Account account where account.cardNumber=:cardNumber");
        hql.setParameter("cardNumber", cardNumber);
        Account result = hql.getSingleResult();
        transaction.commit();
        session.close();
        return result;
    }

    public void updateFund(Account account, double fund) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        account.setFund(fund);
        session.saveOrUpdate(account);
        transaction.commit();
        session.close();
    }

}
