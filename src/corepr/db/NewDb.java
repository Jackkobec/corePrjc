package corepr.db;

import corepr.exceptions.NoTicketFoundException;
import corepr.model.common.Address;
import corepr.model.common.MyDate;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class NewDb {

    private Map<String, PostTicket> tickets = new TreeMap<>();

    public PostTicket findTicket(String ticketId) throws NoTicketFoundException {
        PostTicket postTicket = tickets.get(ticketId);

        if(postTicket == null) {
            throw new NoTicketFoundException("No ticket with id " + ticketId  );
        }

        return postTicket;
    }

    public PostTicket addPostTicket(Client client, Address from, Address sendToAddress, Product product, MyDate estimationDate, MyDate currentDate) {
        String ticketNewId = UUID.randomUUID().toString();

        PostTicket postTicket = new PostTicket(ticketNewId, client, new Product[]{product},
                from,sendToAddress, currentDate,estimationDate);

        tickets.put(ticketNewId, postTicket);

        return postTicket;
    }
}
