package ir.maktab.service;

import ir.maktab.dao.ClientDao;
import ir.maktab.model.Client;
import ir.maktab.model.enuramation.ClientHistoryType;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class ClientService {
    ClientDao clientDao = new ClientDao();
    public void addNewClient(Client client) {
        clientDao.saveNewClient(client);
    }

    public List<Client> getClientByName(String name) {
        return clientDao.findClientByName(name);
    }

    public List<Client> getClientByFamily(String family) {
        return clientDao.findClientByFamily(family);
    }

    public Client getClientByNationalNumber(long clientNationalNumber) {
        return clientDao.findClientByNationalNumber(clientNationalNumber);
    }

    public void updateClientName(Client client, String newName) {
        clientDao.updateClientName(client, newName);
    }

    public void updateClientFamily(Client client, String newFamily) {
        clientDao.updateClientFamily(client, newFamily);
    }

    public void updateClientNationalNumber(Client client, long newNationalNumber) {
        clientDao.updateClientNationalNumber(client, newNationalNumber);
    }

    public void updateClientHistory(Client client, ClientHistoryType history) {
        clientDao.updateClientHistory(client, history);
    }

    public Client getClientByAccountId(long cardNumber) {
        Client client = clientDao.findClientByCardNumber(cardNumber);
        return client;
    }
}
