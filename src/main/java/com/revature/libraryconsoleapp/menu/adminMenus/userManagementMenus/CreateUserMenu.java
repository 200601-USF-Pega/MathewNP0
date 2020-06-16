package com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus;

import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Access;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ConnectionService;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.Scanner;


public class CreateUserMenu implements ISessionMenu {

    private User sessionUser;
    private String userName;
    private String pwd;
    private String firstName;
    private String lastName;
    private String access;
    private ValidationService validationService  = new ValidationService();
    private ConnectionService connectionService = new ConnectionService();
    private UserRepoDB userRepoDB= new UserRepoDB();


    public CreateUserMenu(User sessionUser) {
       this.sessionUser = sessionUser;
    }
    //private MenuFactory menuFactory = new MenuFactory();
    //private IMenu currentMenu;

    @Override
    public void start() {

        ViewClass.printSessionHeader("User Creation", sessionUser);
        prompt();

        Scanner input = new Scanner(System.in);
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        System.out.println("click any key to BACK.");
        input.nextLine().toLowerCase();
        currentMenu =  sessionMenuFactory.changeMenu("user_management", sessionUser);
        currentMenu.start();
    }



    private void prompt(){
        userName = validationService.getValidStringInput("Please enter a new username: ");
        if (userRepoDB.doesUserExist(userName)){
            System.out.println("The user already exists. Try again with a different username.");
            return;
        } else {
            pwd= validationService.getValidStringInput("Please enter a new password: ");

            firstName= validationService.getValidNameInput("Please enter your firstname: ");
            lastName= validationService.getValidNameInput("Please enter your lastname: ");

        }

        User user1 = new User(userName, pwd, firstName, lastName, Access.ADMIN);
        userRepoDB.addUser(user1);
        System.out.println("User created and pushed to the db.");
    }


}

