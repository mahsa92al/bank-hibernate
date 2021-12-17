package ir.maktab.service;

import ir.maktab.dao.AccountDao;
import ir.maktab.model.Account;

/**
 * @author Mahsa Alikhani m-58
 */
public class AccountService {
    AccountDao accountDao = new AccountDao();
    public void addNewAccount(Account account) {
        accountDao.saveNewAccount(account);
    }

    public Account getAccountByCardNumber(long cardNumber) {
        return accountDao.findAccountByCardNumber(cardNumber);
    }

    public void updateFund(Account account, double fund) {
        accountDao.updateFund(account, fund);
    }
}
