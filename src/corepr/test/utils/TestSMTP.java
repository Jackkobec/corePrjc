package corepr.test.utils;

import corepr.controller.DataInitFactory;
import corepr.controller.ManagerController;
import corepr.db.AppDataContainer;
import corepr.model.office.Client;

import java.io.IOException;
import java.util.Arrays;


public class TestSMTP {

    public static void main(String[] args) throws IOException {


        Client testClient = DataInitFactory.clientCreator();

        testClient.setMail("YOUR_MAIL_ADDRESS@gmail.com");
        ManagerController managerController = new ManagerController(new AppDataContainer());

        managerController.createTicket(testClient, DataInitFactory.createAddress(), Arrays.asList(DataInitFactory.productsCreator()));


    }


}


