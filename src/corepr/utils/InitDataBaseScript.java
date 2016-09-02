package corepr.utils;

import corepr.db.AppDataContainer;
import corepr.db.InitDB;


public class InitDataBaseScript {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        InitDB.saveDBToFileAsJson(appDataContainer);
    }
}
