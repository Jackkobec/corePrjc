package corepr.model.office;

import corepr.model.common.Address;


public class PostOffice {

    private static int nextId = 0;

    private String id;
    private Address address;

    public PostOffice(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    public PostOffice(Address address) {
        this.id = Integer.toString(nextId++);
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
