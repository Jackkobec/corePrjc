package corepr.filter;

import corepr.db.AppDataContainer;
import corepr.model.common.Address;
import corepr.model.office.PostTicket;

import java.util.ArrayList;
import java.util.List;


public final class Finder {

    public static List<PostTicket> findByPrice(AppDataContainer appDataContainer, int price) {
        List<PostTicket> list = new ArrayList<>();
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getPrice() == price) {
                list.add(postTicket);
            }
        }
        return list;
    }

    public static List<PostTicket> findByAddress(AppDataContainer appDataContainer, Address address) {
        List<PostTicket> list = new ArrayList<>();
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getFrom().getCity().equals(address.getCity()) &&
                    postTicket.getFrom().getStreet().equals(address.getStreet()) &&
                    postTicket.getFrom().getHouseNum().equals(address.getHouseNum())) {
                list.add(postTicket);
            }
        }
        return list;
    }

    public static List<PostTicket> findByCity(AppDataContainer appDataContainer, String city) {
        List<PostTicket> list = new ArrayList<>();
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getFrom().getCity().equals(city)) {
                list.add(postTicket);
            }
        }
        return list;
    }

    public static List<PostTicket> findByOwnerName(AppDataContainer appDataContainer, String name) {
        List<PostTicket> list = new ArrayList<>();
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getClient().getPassport()
                    .getFullname().toLowerCase()
                    .contains(name.toLowerCase())) {
                list.add(postTicket);
            }
        }
        return list.size() == 0 ? null : list;
    }


}
