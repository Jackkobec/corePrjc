package corepr.test;

import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.model.common.Passport;
import corepr.model.office.Client;


public class TestInheritance {
    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();

        InitDB.initDB(appDataContainer);
      //  System.out.println(appDataContainer.getClients().toString());
        Client client = new Client("+380935612565", new Passport("Basdf dfsdf","GH856545"));
        System.out.println(client.toString());
    }
}
