package corepr.controller.interfaces;

import corepr.exceptions.AppException;
import corepr.model.common.Address;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.common.Product;

import java.util.List;

/**
 *
 */
public interface IClientController {
    PostTicket makeOrder(Client client, Address sendToAddress, List<Product> product) throws AppException;
    PostTicket showTicketById(String ticketId) throws AppException;
    Product showProductById(int ticketId) throws AppException;
    boolean cancelTicket(int ticketId) throws AppException;
    Product takeProduct(int ticketId) throws AppException;
    List<PostTicket> showAllClientTickets(Client client);
}
