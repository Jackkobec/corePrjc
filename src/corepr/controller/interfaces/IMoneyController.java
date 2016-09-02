package corepr.controller.interfaces;

import corepr.model.money.Tax;
import corepr.model.money.Transaction;

/**
 * Created by Lida on 16.07.2016.
 */
public interface IMoneyController {
    Transaction paySalary(String employeeName, String employeeSurname,
                          int salaryAmount, Transaction transaction);
    Tax payTax(Transaction transaction, int income);
    Transaction makePayment(int ourBankAccount, int recipientAccount,
                            int transferAmount, String paymentPurpose);
    Transaction findTransactionByID(String transactionId);




}
