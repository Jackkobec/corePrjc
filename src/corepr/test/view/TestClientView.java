package corepr.test.view;

import corepr.controller.DataInitFactory;
import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.model.office.Client;
import corepr.utils.factory.ControllerFactory;
import corepr.view.frame.ClientView;


/**
 * Created by pashc on 20.08.2016.
 */
public class TestClientView {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        Client client = DataInitFactory.clientCreator();
        ClientView clientView = new ClientView(ControllerFactory.getClientController(), client);
    }
}
