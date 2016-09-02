package corepr.controller;

import corepr.controller.interfaces.IMoneyController;
import corepr.db.AppDataContainer;
import corepr.model.money.Salary;
import corepr.model.money.Tax;
import corepr.model.money.Transaction;


/**
 *
 */
public class MoneyController implements IMoneyController {
    public static final int TAX_RATE_PERCENTAGE = 18;
    protected AppDataContainer appDataContainer;

    public MoneyController (AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    @Override
    public Transaction paySalary(String employeeName, String employeeSurname, int salaryAmount, Transaction transaction) {
        Salary salary = new Salary(transaction,salaryAmount,employeeName,employeeSurname);

        appDataContainer.getTransactions().add(salary);

        return salary;
    }

    @Override
    public Tax payTax( Transaction transaction, int income) {
        Transaction taxPayment = new Transaction(transaction.getOurBankAccount(), transaction.getRecipientAccount(),
                transaction.getTransferAmount(), transaction.getPaymentPurpose());
        Tax tax = new Tax(income,taxPayment);

        appDataContainer.getTransactions().add(tax);

        return tax;
    }

    @Override
    public Transaction makePayment( int ourBankAccount, int recipientAccount,
                                   int transferAmount,String paymentPurpose) {
        Transaction transaction = new Transaction(ourBankAccount, recipientAccount, transferAmount, paymentPurpose);

        appDataContainer.getTransactions().add(transaction);

        return transaction;
    }

    @Override
    // fill db for test
    public Transaction findTransactionByID(String transactionId) {
        for(Transaction transaction: appDataContainer.getTransactions()) {
            if (transaction.getTransactionId().equals(String.valueOf(transactionId))) {
                return transaction;
            }
        }
        return null;
    }

}
