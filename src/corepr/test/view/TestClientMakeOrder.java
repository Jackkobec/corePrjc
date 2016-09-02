package corepr.test.view;

import corepr.db.AppDataContainer;
import corepr.model.common.Passport;
import corepr.model.office.Client;
import corepr.view.frame.ClientMakeOrder;

/**
 * Created by sasha on 20.08.2016.
 */
public class TestClientMakeOrder {
    public static void main(String[] args) {

        AppDataContainer app = new AppDataContainer();
        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport, "df@df.com");

        ClientMakeOrder makeOrder = new ClientMakeOrder(app, client);
    }
}
