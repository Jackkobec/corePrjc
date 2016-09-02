package corepr.controller.interfaces;

import corepr.exceptions.ValidationException;
import corepr.model.common.Address;
import corepr.model.common.Passport;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;

import java.util.List;


public interface IManagerController {
    PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) throws ValidationException;
    PostTicket filterTicketById(String ticketId) throws ValidationException;
    List<PostTicket> showTicketByClientPhone(String phone) throws ValidationException;
    Client getClient(String phone) throws ValidationException;
    Client addClient(Passport passport, String phone) throws ValidationException;
    Client addClient(Passport passport, String phone, String mail) throws ValidationException;
    List<PostTicket> sortTicketsByAddress();
    List<Client>sortClientsByName();
    List<PostTicket> sortTicketsByPrice();
    List<PostTicket> sortTicketsById();
    List findByPrice(int price);
    List<PostTicket> findByAddress(Address address);
    List<PostTicket> findByCity(String city);
    List<PostTicket> findByOwnerName(String name);


    boolean cancelTicket(int ticketId);
}
