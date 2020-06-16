package com.revature.libraryconsoleapp.menu;

import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.LoginService;
import com.revature.libraryconsoleapp.service.ValidationService;

public class LoginMenu implements IMenu {
    private String userName;
    private String password;
    private ValidationService validationService = new ValidationService();
    private LoginService loginService = new LoginService();
    private SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
    private ISessionMenu userMenu;

    public void start() {
        ViewClass.printSessionHeader("Login");
        userName = validationService.getValidStringInput("Please enter your username: ");
        password = validationService.getValidStringInput("Please enter your password: ");
        User userFound= loginService.checkForUser(userName, password);
        //System.out.println(userFound);

        if(userFound != null) {
            System.out.printf("The user %s is logged in.\n", userFound.getUserName());
            userMenu = sessionMenuFactory.changeMenu("user_main_menu", userFound);
            userMenu.start();

        } else { //this goes to the main menu.
            System.out.println("Wrong username / password.");
        }
        //return userFound;
    }
}
