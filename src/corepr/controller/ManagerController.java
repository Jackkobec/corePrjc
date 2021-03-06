package corepr.controller;


import corepr.controller.interfaces.IManagerController;
import corepr.db.AppDataContainer;
import corepr.exceptions.ValidationException;
import corepr.filter.*;
import corepr.model.common.Address;
import corepr.model.common.MyDate;
import corepr.model.common.Passport;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.office.TicketStatus;
import corepr.utils.email.smtp.SMTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 */
public class ManagerController implements IManagerController {

    public static final int DAYS_IN_ROAD = 2;
    private static final String DEFAULT_ATTACHMENT = "resources/orderTemplate.rtf";



    protected AppDataContainer appDataContainer;
    protected Address addressFrom = DataInitFactory.createAddress();
    public ManagerController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) {
        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+ DAYS_IN_ROAD);

        Product[] productsArray = product.toArray(new Product[product.size()]);

        PostTicket postTicket = new PostTicket(client, productsArray, addressFrom, sendToAdress,
                currentTime, estimationArrivalDate);

        appDataContainer.getTickets().add(postTicket);

        try {
            SMTP.sendMail(client,postTicket,DEFAULT_ATTACHMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return postTicket;
    }


    @Override
    public PostTicket filterTicketById(String ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getId().equals(String.valueOf(ticketId))) {
                return postTicket;
            }

        }
        return null;
    }

    @Override
    public List<PostTicket> showTicketByClientPhone(String phone) {
        List<PostTicket> postTicketList = new ArrayList<>();
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getClient().getPhone().equals(String.valueOf(phone))){
                postTicketList.add(postTicket);
            }
        }
        return postTicketList.size()==0? null : postTicketList;
    }

    @Override
    public Client getClient(String phone) {

        List<Client> clientArr = appDataContainer.getClients();
        for(Client client : clientArr) {
            Client iterClient = client;
            if(iterClient.getPhone().equals(phone)) {
                return iterClient;
            }
        }
        return null;
    }

    @Override
    public Client addClient(Passport passport, String phone) {
        Client client = new Client(phone, passport);
        sendLogin(client);
        appDataContainer.getClients().add(client);
        appDataContainer.getUsers().put(client.getLogin(), client);
        return client;
    }

    @Override
    public Client addClient(Passport passport, String phone, String mail) throws ValidationException {
        Client client = new Client(phone, passport, mail);
        sendLogin(client);
        appDataContainer.getClients().add(client);
        appDataContainer.getUsers().put(client.getLogin(), client);
        return client;
    }

    private void sendLogin(Client client) {
        try {
            SMTP.sendLoginAndPass(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<PostTicket> sortTicketsByAddress() {
         List <PostTicket> tickets = new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new AddressComparator());
       return tickets;
    }

    @Override
    public List<Client> sortClientsByName() {
        List <Client> clients = new ArrayList<>();
        clients = appDataContainer.getClients();
        clients.sort(new OwnerNameComparator());
        return clients;
    }

    @Override
    public List<PostTicket> sortTicketsByPrice(){
        List<PostTicket> tickets= new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new PriceComparator());
        return tickets;
    }

    @Override
    public List<PostTicket> sortTicketsById() {
        List<PostTicket> tickets= new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new TicketIdComparator());
       return tickets;
    }

    @Override
    public List findByPrice(int price) {

        return Finder.findByPrice(appDataContainer, price);
    }

    public ManagerController() {
        super();
    }

    @Override
    public List<PostTicket> findByAddress(Address address) {
        return  Finder.findByAddress(appDataContainer,address);
    }

    @Override
    public List<PostTicket> findByCity(String city) {
        return Finder.findByCity(appDataContainer,city);
    }

    @Override
    public List<PostTicket> findByOwnerName(String name) {
        return Finder.findByOwnerName(appDataContainer,name);
    }

    @Override
    public boolean cancelTicket(int ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                postTicket.setStatus(TicketStatus.CANCELED);
                return true;
            }
        }

        return false;
    }

}
