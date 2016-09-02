package corepr.controller.interfaces;

import corepr.model.office.User;


public interface ILoginController {
    User findUser(String login, String password);
}
