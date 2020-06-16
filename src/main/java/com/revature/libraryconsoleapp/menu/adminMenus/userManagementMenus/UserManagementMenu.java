package com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus;

import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.User;

import java.util.Scanner;

public class UserManagementMenu implements ISessionMenu{
    private User user;

    public UserManagementMenu(User user) {
       this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("User Management", user);
        System.out.println(
                "Enter any of these options.\n" +
                "[1] Create User\n" +
                "[2] Delete User\n" +
                "[3] Edit/Ban User\n" +
                "[4] Look at all the users\n" +
                "[b] BACK."
        );

        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        while(true) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    currentMenu = sessionMenuFactory.changeMenu("create_user_menu", user);
                    currentMenu.start();
                    break;
                case "2":
                    currentMenu =  sessionMenuFactory.changeMenu("delete_user_menu", user);
                    currentMenu.start();
                    break;
                case "3":
                    currentMenu =  sessionMenuFactory.changeMenu("edit_a_user", user);
                    currentMenu.start();
                    break;
                case "4":
                    currentMenu =  sessionMenuFactory.changeMenu("look_at_users", user);
                    currentMenu.start();
                    break;
                case "b":
                    currentMenu = sessionMenuFactory.changeMenu("user_main_menu", user);
                    currentMenu.start();
                    break;
                default:
                    System.out.println("Please press the given option inside the [''].");
            }
        }

    }
}
