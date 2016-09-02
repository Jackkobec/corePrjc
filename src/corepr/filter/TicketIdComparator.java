package corepr.filter;

import corepr.model.office.PostTicket;

import java.util.Comparator;


public class TicketIdComparator implements Comparator<PostTicket>{
    @Override
    public int compare(PostTicket o1, PostTicket o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
