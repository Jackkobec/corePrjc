package corepr.controller.proxy;

import corepr.controller.interfaces.IManagerController;
import corepr.exceptions.ValidationException;
import corepr.model.common.Address;
import corepr.model.common.Passport;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.utils.logging.LogContainer;

import java.util.List;


public class LoggingManagerControllerProxy implements IManagerController {

    protected IManagerController managerController;

    public LoggingManagerControllerProxy(IManagerController controller) {
        managerController = controller;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) throws ValidationException {
        String products = "";
        for(Product p : product) {
            products += p.getName() + ", ";
        }
        products = products.substring(0, products.length()-1);

        LogContainer.logEvent("Manager: there was an attempt to create ticket with client number " + client.getPhone() + ", destination " + sendToAdress.getCity() + ", product " + products);
        return managerController.createTicket(client, sendToAdress, product);
    }

    @Override
    public PostTicket filterTicketById(String ticketId) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to filter ticket by Id " + ticketId);
        return managerController.filterTicketById(ticketId);
    }

    @Override
    public List<PostTicket> showTicketByClientPhone(String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to show ticket by client phone " + phone);
        return managerController.showTicketByClientPhone(phone);
    }

    @Override
    public Client getClient(String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to get client by phone " + phone);
        return managerController.getClient(phone);
    }

    @Override
    public Client addClient(Passport passport, String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to add client with passport " + passport.getNumber() + " and phone " + phone);
        return managerController.addClient(passport, phone);
    }

    @Override
    public Client addClient(Passport passport, String phone, String mail) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to add client with passport " + passport.getNumber() + " and phone " + phone);
        return managerController.addClient(passport, phone, mail);
    }

    @Override
    public List<PostTicket> sortTicketsByAddress() {
        return null;
    }

    @Override
    public List<Client> sortClientsByName() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsByPrice() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsById() {
        return null;
    }

    @Override
    public List findByPrice(int price) {
        return null;
    }

    public LoggingManagerControllerProxy() {
        super();
    }

    @Override
    public List<PostTicket> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<PostTicket> findByCity(String city) {
        return null;
    }

    @Override
    public List<PostTicket> findByOwnerName(String name) {
        return null;
    }

    @Override
    public boolean cancelTicket(int ticketId) {
        return false;
    }

}
