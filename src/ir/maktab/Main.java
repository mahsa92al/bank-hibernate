package ir.maktab;

import ir.maktab.exception.NotFoundException;
import ir.maktab.model.Account;
import ir.maktab.model.Client;
import ir.maktab.model.Updates;
import ir.maktab.model.enuramation.AccountType;
import ir.maktab.model.enuramation.ClientHistoryType;
import ir.maktab.model.enuramation.UpdateType;
import ir.maktab.service.AccountService;
import ir.maktab.service.ClientService;
import ir.maktab.service.TransactionService;
import ir.maktab.service.UpdateService;

import java.util.*;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ClientService clientService;
    private static AccountService accountService;
    private static TransactionService transactionService;
    private static UpdateService updateService;

    public static void main(String[] args) {
        clientService = new ClientService();
        accountService = new AccountService();
        transactionService = new TransactionService();
        updateService = new UpdateService();
        int choice;
        do{
            System.out.println("1.create new client\n2.create new account\n" +
                    "3.find client by name\n4.find client by family\n5.find client by card number\n" +
                    "6.change name\n7.change family\n8.change national number\n9.change client history\n" +
                    "10.withdraw\n11.deposit\n12.Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    creatNewClient();
                    break;
                case 2:
                    try{
                        createAndAssignNewAccountToAClient();
                    }catch (NotFoundException e){
                        e.getMessage();
                    }
                    break;
                case 3:
                    viewClientByName();
                    break;
                case 4:
                    viewClientByFamily();
                    break;
                case 5:
                    viewClientByCardNumber();
                    break;
                case 6:
                    updateClientName();
                    break;
                case 7:
                    updateClientFamily();
                    break;
                case 8:
                    updateClientNationalNumber();
                    break;
                case 9:
                    updateClientHistory();
                    break;
                case 10:
                    withdraw();
                    break;
                case 11:
                    deposit();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while (choice != 12);
    }

    private static void deposit() {
        System.out.println("card number:");
        long cardNumber = scanner.nextLong();
        System.out.println("amount:");
        double amount = scanner.nextDouble();
        Account foundAccount = accountService.getAccountByCardNumber(cardNumber);
        double fund = transactionService.deposit(foundAccount, amount);
        accountService.updateFund(foundAccount, fund);
        Updates update = new Updates();
        update.setUpdateType(UpdateType.FUND_CHANGE);
        update.setAccount(foundAccount);
        update.setClient(foundAccount.getClient());
        updateService.addNewUpdate(update);
    }

    private static void withdraw() {
        System.out.println("card number:");
        long cardNumber = scanner.nextLong();
        System.out.println("amount:");
        double amount = scanner.nextDouble();
        Account foundAccount = accountService.getAccountByCardNumber(cardNumber);
        double fund = 0d;
        try {
            fund = transactionService.withdraw(foundAccount, amount);
        } catch (Exception e) {
            e.getMessage();
        }
        accountService.updateFund(foundAccount, fund);
        Updates update = new Updates();
        update.setUpdateType(UpdateType.FUND_CHANGE);
        update.setAccount(foundAccount);
        update.setClient(foundAccount.getClient());
        updateService.addNewUpdate(update);
    }

    private static void updateClientHistory() {
        System.out.println("national number:");
        long nationalNumber = scanner.nextLong();
        Client foundClient = clientService.getClientByNationalNumber(nationalNumber);
        System.out.println("1.good history\n2.bad history\n3.no history");
        int selectedHistory = scanner.nextInt();
        if(selectedHistory == 1){
            clientService.updateClientHistory(foundClient, ClientHistoryType.GOOD_HISTORY);
        }else if(selectedHistory == 2){
            clientService.updateClientHistory(foundClient, ClientHistoryType.BAD_HISTORY);
        }else if(selectedHistory == 3){
            clientService.updateClientHistory(foundClient, ClientHistoryType.NO_HISTORY);
        }else {
            throw new NotFoundException("not found.");
        }
        Updates update = new Updates();
        update.setUpdateType(UpdateType.HISTORY_CHANGE);
        update.setClient(foundClient);
        updateService.addNewUpdate(update);
    }

    private static void updateClientNationalNumber() {
        System.out.println("national number:");
        long nationalNumber = scanner.nextLong();
        Client foundClient = clientService.getClientByNationalNumber(nationalNumber);
        System.out.println("new national number:");
        long newNationalNumber = scanner.nextLong();
        clientService.updateClientNationalNumber(foundClient, newNationalNumber);
        Updates update = new Updates();
        update.setUpdateType(UpdateType.NATIONAL_NUMBER_CHANGE);
        update.setClient(foundClient);
        updateService.addNewUpdate(update);
    }

    private static void updateClientFamily() {
        System.out.println("national number:");
        long nationalNumber = scanner.nextLong();
        Client foundClient = clientService.getClientByNationalNumber(nationalNumber);
        System.out.println("new family:");
        String newFamily = scanner.next();
        clientService.updateClientFamily(foundClient, newFamily);
        Updates update = new Updates();
        update.setUpdateType(UpdateType.FAMILY_CHANGE);
        update.setClient(foundClient);
        updateService.addNewUpdate(update);
    }

    private static void updateClientName() {
        System.out.println("national number:");
        long nationalNumber = scanner.nextLong();
        Client foundClient = clientService.getClientByNationalNumber(nationalNumber);
        System.out.println("new name:");
        String newName = scanner.next();
        clientService.updateClientName(foundClient, newName);
        Updates update = new Updates();
        update.setUpdateType(UpdateType.NAME_CHANGE);
        update.setClient(foundClient);
        updateService.addNewUpdate(update);
    }

    public static void creatNewClient(){
        System.out.println("name:");
        String name = scanner.next();
        System.out.println("family:");
        String family = scanner.next();
        System.out.println("national number:");
        long nationalNumber = scanner.nextLong();
        Client client = new Client();
        client.setName(name);
        client.setFamily(family);
        client.setNationalNumber(nationalNumber);
        client.setClientHistoryType(ClientHistoryType.NO_HISTORY);
        clientService.addNewClient(client);
    }

    public static void createAndAssignNewAccountToAClient(){
        System.out.println("client national number:");
        long clientNationalNumber = scanner.nextLong();
        Client client = clientService.getClientByNationalNumber(clientNationalNumber);
        System.out.println("account type:\n1.short term\n2.long term\n3.current");
        int selectedAccountType = scanner.nextInt();
        Account account = new Account();
        account.setAccountNumber(Integer.parseInt(generateRandom(6)));
        account.setCardNumber(Long.parseLong(generateRandom(16)));
        account.setExpireDate(calculateFourYearsAfter());
        if(selectedAccountType == 1){
            account.setAccountType(AccountType.SHORT_TERM);
        }else if(selectedAccountType == 2){
            account.setAccountType(AccountType.LONG_TERM);
        }else if(selectedAccountType == 3){
            account.setAccountType(AccountType.CURRENT);
        }
        else{
            throw new NotFoundException("not found.");
        }
        account.setFund(100000d);
        account.setCcv2(Integer.parseInt(generateRandom(4)));
        account.setClient(client);
        accountService.addNewAccount(account);
        client.getAccounts().add(account);
    }

    public static void viewClientByName(){
        System.out.println("name:");
        String name = scanner.next();
        List<Client> foundClient = clientService.getClientByName(name);
        System.out.println(foundClient);
    }

    private static void viewClientByFamily() {
        System.out.println("family:");
        String family = scanner.next();
        List<Client> foundClient = clientService.getClientByFamily(family);
        System.out.println(foundClient);
    }

    private static void viewClientByCardNumber() {
        System.out.println("card number:");
        long cardNumber  = scanner.nextLong();
        //Account foundAccount = accountService.getAccountByCardNumber(cardNumber);
        Client foundClient = clientService.getClientByAccountId(cardNumber);
        System.out.println(foundClient);
    }

    public static String generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return String.valueOf(digits);
    }

    public static Date calculateFourYearsAfter(){
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        cal.add(Calendar.YEAR, 4);
        Date fourNextYear = cal.getTime();
        return fourNextYear;
    }
}
