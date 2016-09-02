package corepr.controller;

import corepr.controller.interfaces.IClientController;
import corepr.db.AppDataContainer;
import corepr.model.common.Address;
import corepr.model.common.MyDate;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.office.TicketStatus;
import corepr.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public class ClientController implements IClientController {

    public static final int DAYS_IN_ROAD = 2;

    protected AppDataContainer appDataContainer;

    public ClientController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, List<Product> product) {


        MyDate currentTime = TimeUtils.getCurrentDate();

        currentTime.setDay(currentTime.getDay() + DAYS_IN_ROAD);

        PostTicket postTicket = new PostTicket(client, (Product[]) product.toArray(), new Address("Kiyv","Lesi","22"), sendToAddress,
                currentTime, currentTime);

        appDataContainer.getTickets().add(postTicket);

        return postTicket;
    }

    @Override
    public PostTicket showTicketById(String ticketId) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                return postTicket;
            }
        }
        return null;
    }

    @Override
    public Product showProductById(int ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                return postTicket.getProducts()[0];
            }
        }

        return null;
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

    @Override
    public Product takeProduct(int ticketId) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                if(postTicket.getStatus()==TicketStatus.DONE || postTicket.getStatus()==TicketStatus.CANCELED) {
                    return postTicket.getProducts()[0];
                }

            }
        }

        return null;
    }

    @Override
    public List<PostTicket> showAllClientTickets(Client client) {

        List<PostTicket> tickets = new ArrayList<>();
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getClient().equals(client)){
                tickets.add(postTicket);
            }
        }
        return tickets;

    }

}
