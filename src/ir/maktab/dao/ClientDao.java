package ir.maktab.dao;

import ir.maktab.model.Client;
import ir.maktab.model.enuramation.ClientHistoryType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class ClientDao extends BaseDao{
    public void saveNewClient(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public List<Client> findClientByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> hql = session.createQuery("from Client client where client.name=:name");
        hql.setParameter("name", name);
        List<Client> resultList = hql.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    public List<Client> findClientByFamily(String family) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> hql = session.createQuery("from Client client where client.family=:family");
        hql.setParameter("family", family);
        List<Client> resultList = hql.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    public Client findClientByNationalNumber(long nationalNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> hql = session.createQuery("from Client client where client.nationalNumber=:nationalNumber");
        hql.setParameter("nationalNumber", nationalNumber);
        Client result = hql.getSingleResult();
        transaction.commit();
        session.close();
        return result;
    }

    public void updateClientName(Client client, String newName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        client.setName(newName);
        session.saveOrUpdate(client);
        transaction.commit();
        session.close();
    }

    public void updateClientFamily(Client client, String newFamily) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        client.setFamily(newFamily);
        session.saveOrUpdate(client);
        transaction.commit();
        session.close();
    }

    public void updateClientNationalNumber(Client client, long newNationalNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        client.setNationalNumber(newNationalNumber);
        session.saveOrUpdate(client);
        transaction.commit();
        session.close();
    }

    public void updateClientHistory(Client client, ClientHistoryType history) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        client.setClientHistoryType(history);
        session.saveOrUpdate(client);
        transaction.commit();
        session.close();
    }

    public Client findClientByCardNumber(long cardNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> hql = session.createQuery("select c from Client c join c.accounts a where a.cardNumber=:cardNumber");
        hql.setParameter("cardNumber", cardNumber);
        Client client = hql.getSingleResult();
        transaction.commit();
        session.close();
        return client;
    }
}
