package corepr.controller;

import corepr.controller.interfaces.IClientController;
import corepr.db.NewDb;
import corepr.exceptions.AppException;
import corepr.exceptions.NoTicketFoundException;
import corepr.model.common.Address;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.office.TicketStatus;

import java.util.List;

/**
 *
 */
public class ClientControllerNewDB implements IClientController {

    private NewDb newDb;

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, List<Product> product)
            throws AppException {

        PostTicket postTicket = null;//newDb.addPostTicket(client, sendToAddress, product);

        return postTicket;
    }

    @Override
    public PostTicket showTicketById(String ticketId) throws AppException {

        // validation
        // preprocess -> logging, prepare data, session

        try {
            PostTicket found = newDb.findTicket(ticketId);
            return found;
        } catch (NoTicketFoundException ex){
            throw new AppException(ex.getMessage());
        }
    }

    @Override
    public Product showProductById(int ticketId) throws AppException {
        try {
            PostTicket found = newDb.findTicket(String.valueOf(ticketId));
            return found.getProducts()[0];
        } catch (NoTicketFoundException ex){
            throw new AppException(ex.getMessage());
        }
    }

    @Override
    public boolean cancelTicket(int ticketId) throws AppException {
        try {
            PostTicket found = newDb.findTicket(String.valueOf(ticketId));
            found.setStatus(TicketStatus.CANCELED);
            return true;
        } catch (NoTicketFoundException ex){
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Product takeProduct(int ticketId) throws AppException {
        return null;
    }

    @Override
    public List<PostTicket> showAllClientTickets(Client client) {
        return null;
    }
}
