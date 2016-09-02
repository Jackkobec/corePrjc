package corepr.validator;

import corepr.model.common.Address;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.common.Product;


public interface IValidator {
    ResultValidator validation(Address address);
    ResultValidator validation(Client client);
    ResultValidator validation(Product product);
    ResultValidator validation(PostTicket postTicket);
}
