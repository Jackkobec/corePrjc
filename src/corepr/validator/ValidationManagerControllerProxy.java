package corepr.validator;

import corepr.controller.interfaces.IManagerController;
import corepr.exceptions.ValidationException;
import corepr.model.common.Address;
import corepr.model.common.Passport;
import corepr.model.common.Product;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;

import java.util.List;


public class ValidationManagerControllerProxy implements IManagerController {

    private IManagerController managerController;
    private Validator validator;

    public ValidationManagerControllerProxy(IManagerController originalController, Validator validator) {
        this.managerController = originalController;
        this.validator = validator;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) throws ValidationException {

        String err = "";

        if (!validator.validation(client).getErr()) err += validator.validation(client).getTextErr();
        if (!validator.validation(sendToAdress).getErr()) err += validator.validation(sendToAdress).getTextErr();
        for(Product p : product) {
            if (!validator.validation(p).getErr()) err += validator.validation(p).getTextErr();
        }
        if (!err.equals("")) throw new ValidationException(err);
        else return managerController.createTicket(client, sendToAdress, product);
    }

    @Override
    public PostTicket filterTicketById(String ticketId) throws ValidationException {
        try {
            int num = Integer.parseInt(ticketId);
            PostTicket pt = managerController.filterTicketById(ticketId);
            if (pt != null) {
                return pt;
            } else {
                throw new ValidationException("No ticket was found per inputted Id.");
            }
        } catch (NumberFormatException ex) {
            throw new ValidationException("Inputted ticket Id is not numeric.");
        }
    }

    @Override
    public List<PostTicket> showTicketByClientPhone(String phone) throws ValidationException {
        if (validator.isPhone(phone)) {
            List<PostTicket> ps = managerController.showTicketByClientPhone(phone);
            if (ps != null) {
                return ps;
            } else {
                throw new ValidationException("No ticket was found per inputted phone.");
            }
        } else {
            throw new ValidationException("Phone number is not correct. Please verify.");
        }
    }

    @Override
    public Client getClient(String phone) throws ValidationException {
        if (validator.isPhone(phone)) {
            Client cl = managerController.getClient(phone);
            if (cl != null) {
                return cl;
            } else {
                throw new ValidationException("No client was found per inputted Id.");
            }
        } else {
            throw new ValidationException("Phone number is not correct. Please verify.");
        }
    }

    @Override
    public Client addClient(Passport passport, String phone) throws ValidationException {

        String err = "";

        if (validator.isPhone(phone)) {
        } else {
            err += "Phone number is not correct. Please verify.";
        }
        if (validator.isPassport(passport)) {
        } else {
            if (err.length() > 0) err += "\n";
            err += "Passport info or full name is not correct. Please verify.";
        }

        if (err.length() > 0) {
            throw new ValidationException(err);
        } else {
            return managerController.addClient(passport, phone);
        }
    }

    @Override
    public Client addClient(Passport passport, String phone, String mail) throws ValidationException {

        String err = "";

        if (validator.isPhone(phone)) {
        } else {
            err += "Phone number is not correct. Please verify.";
        }
        if (validator.isPassport(passport)) {
        } else {
            if (err.length() > 0) err += "\n";
            err += "Passport info or full name is not correct. Please verify.";
        }

        if (err.length() > 0) {
            throw new ValidationException(err);
        } else {
            return managerController.addClient(passport, phone, mail);
        }
    }


    @Override
    public List<PostTicket> sortTicketsByAddress() {
        return null;
    }

    @Override
    public List<Client> sortClientsByName() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsByPrice() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsById() {
        return null;
    }

    @Override
    public List findByPrice(int price) {
        return null;
    }

    public ValidationManagerControllerProxy() {
        super();
    }

    @Override
    public List<PostTicket> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<PostTicket> findByCity(String city) {
        return null;
    }

    @Override
    public List<PostTicket> findByOwnerName(String name) {
        return null;
    }

    @Override
    public boolean cancelTicket(int ticketId) {
        return false;
    }

}
