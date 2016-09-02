package corepr.filter;

import corepr.model.office.Client;

import java.util.Comparator;


public class OwnerNameComparator implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        return o1.getPassport().getFullname().compareTo(o2.getPassport().getFullname());
    }
}
