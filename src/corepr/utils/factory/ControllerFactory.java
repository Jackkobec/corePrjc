package corepr.utils.factory;

import corepr.controller.ClientController;
import corepr.controller.LoginController;
import corepr.controller.ManagerController;
import corepr.controller.interfaces.IClientController;
import corepr.controller.interfaces.ILoginController;
import corepr.controller.interfaces.IManagerController;
import corepr.controller.proxy.LoggingClientControllerProxy;
import corepr.controller.proxy.LoggingManagerControllerProxy;
import corepr.db.AppDataContainer;
import corepr.db.InitDB;
import corepr.utils.AppConstants;
import corepr.validator.ValidationClientControllerProxy;
import corepr.validator.ValidationManagerControllerProxy;
import corepr.validator.Validator;

import java.io.IOException;

/**
 * Created by serhii on 21.08.16.
 */
public class ControllerFactory {

    private static AppDataContainer appDataContainer;
    private static Validator validator;

    static {
        String location = ControllerFactory.class.getResource(AppConstants.DB_JSON_PATH).getPath();
        try {
            appDataContainer = InitDB.loadDBAsJson(location);
            validator = new Validator();


            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    InitDB.saveDBToFileAsJson(appDataContainer);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IClientController getClientController() {
        return new ValidationClientControllerProxy(
                new LoggingClientControllerProxy(
                        new ClientController(appDataContainer)), validator);
    }

    public static IManagerController getManagerController() {
        return new ValidationManagerControllerProxy(
                new LoggingManagerControllerProxy(
                        new ManagerController(appDataContainer)), validator);
    }

    public static ILoginController getLoginController(){
        return new LoginController(appDataContainer);
    }
}
