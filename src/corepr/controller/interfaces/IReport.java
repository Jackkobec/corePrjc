package corepr.controller.interfaces;

import corepr.model.money.Report;
import corepr.model.money.Transaction;
import corepr.model.office.Employee;
import corepr.model.office.PostTicket;

public interface IReport {
    Report finaleReport(int income, Transaction[] transaction, Employee[] employees, PostTicket[] postTickets);

}
