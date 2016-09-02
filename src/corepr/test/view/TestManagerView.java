package corepr.test.view;

import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.utils.factory.ControllerFactory;
import corepr.view.frame.ManagerView;

/**
 * Created by Serhii Fursenko on 17.08.16.
 * mail to fyrsenko@gmail.com
 */
public class TestManagerView {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        ManagerView managerView = new ManagerView(ControllerFactory.getManagerController(),ControllerFactory.getClientController());

        managerView.showManagerView();
    }
}
