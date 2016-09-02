package corepr.controller;

import corepr.controller.interfaces.ILoginController;
import corepr.db.AppDataContainer;
import corepr.model.office.User;

/**
 *
 */
public class LoginController implements ILoginController {

    private AppDataContainer appDataContainer;

    public LoginController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public User findUser(String login, String password){
        User user = appDataContainer.getUsers().get(login);
        if ((user != null) && user.getPassword().equals(password)) return user;

        return null;
    }
}
