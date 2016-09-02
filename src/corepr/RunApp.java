package corepr;

import corepr.controller.interfaces.IClientController;
import corepr.controller.interfaces.ILoginController;
import corepr.controller.interfaces.IManagerController;
import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.exceptions.ControllerException;
import corepr.exceptions.InputDataException;
import corepr.exceptions.LogException;
import corepr.exceptions.ValidationException;
import corepr.utils.factory.ControllerFactory;
import corepr.view.frame.ManagerView;

import static corepr.db.InitDB.initDB;

public class RunApp {

    public static void main(String[] args) throws ControllerException, InputDataException, ValidationException, LogException {
InitDB db = new InitDB();
        initDB(new AppDataContainer());
        try {


            IManagerController managerController = ControllerFactory.getManagerController();

            IClientController clientController = ControllerFactory.getClientController();

            ILoginController loginController = ControllerFactory.getLoginController();

            /*LoginFrame loginPassFrame = new LoginFrame(
                    loginController,managerController,clientController);*/
           ManagerView cvv =  new ManagerView(managerController,clientController );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
