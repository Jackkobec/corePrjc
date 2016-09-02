package corepr.test.controller;

import corepr.controller.interfaces.IPostController;
import corepr.controller.PostController;
import corepr.db.AppDataContainer;
import corepr.db.InitDB;


public class TestPostController {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();

        InitDB.initDB(appDataContainer);

        IPostController postController = new PostController(appDataContainer);
        postController.showOfficesOnMap();
    }

}
