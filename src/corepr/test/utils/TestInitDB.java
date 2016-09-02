package corepr.test.utils;

import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.utils.logging.LogContainer;


public class TestInitDB {

    public static void main(String[] args) {
        String location = "resources/db.json";
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        //InitDB.saveDBToFileAsJson(appDataContainer);
        String logs = "olololo";
        String logs1 = "sada";
        //InitDB.saveLogsToFile(logs);
      //  LogContainer logContainer = new LogContainer();
        LogContainer.logEvent(logs);
        LogContainer.logEvent(logs1);
      //  LogContainer.logEvent(logs);
/*
        try {
            String res = InitDB.loadDB(location);
            System.out.println("load was ok");
            System.out.println(res);
            AppDataContainer appDataContainer1 = new AppDataContainer();
            appDataContainer1 = InitDB.loadDBAsJson(location);
            System.out.println(appDataContainer1.getTickets().get(0).asString());
        } catch (IOException e) {
            e.printStackTrace();
        } */
    }
}
