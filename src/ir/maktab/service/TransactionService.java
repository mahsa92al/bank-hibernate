package ir.maktab.service;

import ir.maktab.dao.TransactionDao;
import ir.maktab.model.Account;
import ir.maktab.model.Transaction;
import ir.maktab.model.enuramation.TransactionType;

/**
 * @author Mahsa Alikhani m-58
 */
public class TransactionService {
    TransactionDao transactionDao = new TransactionDao();

    public double withdraw(Account account, double amount) throws Exception {
        double fund = account.getFund();
        if(fund < 30000d){
            throw new Exception("your fund is less than 30000T.");
        }
        if(amount >= fund){
            throw new Exception("the amount is bigger than your fund");
        }
        if(fund <= amount + 30000d){
            throw new Exception("your fund is not enough.");
        }
        fund = fund - amount;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transactionDao.saveNewTransaction(transaction);
        return fund;
    }

    public double deposit(Account account, double amount) {
        double fund = account.getFund();
        fund = fund + amount;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transactionDao.saveNewTransaction(transaction);
        return fund;
    }
}
