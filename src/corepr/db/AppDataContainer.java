package corepr.db;

import corepr.model.money.Report;
import corepr.model.money.Transaction;
import corepr.model.office.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




// todo find good collections for Container
public class AppDataContainer {

    private List<Client> clients            = new ArrayList<>();
    private List<PostTicket> tickets        = new ArrayList<>();
    private List<Driver> drivers            = new ArrayList<>();
    private List<Transaction> transactions  = new ArrayList<>();
    private List<Employee> employees        = new ArrayList<>();
    private List<Report> reports            = new ArrayList<>();
    private List<PostOffice> postOffices    = new ArrayList<>();
    private Map<String, User> users         = new HashMap<>();



    public AppDataContainer() {
    }

    public List<PostOffice> getPostOffices() {
        return postOffices;
    }

    public void setPostOffices(List<PostOffice> postOffices) {
        this.postOffices = postOffices;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<PostTicket> getTickets() {
        return tickets;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String,User> users) {
        this.users = users;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setTickets(List<PostTicket> tickets) {
        this.tickets = tickets;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
