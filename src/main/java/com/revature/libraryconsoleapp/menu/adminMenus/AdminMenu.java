package com.revature.libraryconsoleapp.menu.adminMenus;


import com.revature.libraryconsoleapp.menu.*;
import com.revature.libraryconsoleapp.models.User;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AdminMenu implements ISessionMenu {

    private User user;

    public AdminMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Admin", user);
        System.out.println("Options");
        System.out.println(
                "[1] User Management\n" +
                "[2] Inventory/Book Management\n" +
                "[3] Other Actions\n" +
                "[x] LOGOUT."
        );

        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu; // note.

        while(true) {
            String userInput = input.nextLine().toLowerCase();
            switch(userInput) {
                case "1":
                   currentMenu = sessionMenuFactory.changeMenu("user_management", user);
                   currentMenu.start();
                   break;
                case "2":
                    currentMenu = sessionMenuFactory.changeMenu("inventory_book_management", user);
                    currentMenu.start();
                   break;
                case "3":
                    currentMenu = sessionMenuFactory.changeMenu("other_actions", user);
                    currentMenu.start();
                    break;
                case "x":
                    System.out.println("The user " + user.getUserName() + " has logged out.");
                    currentMenu = new MainMenu();
                    currentMenu.start();
                    break;
                default:
                    System.out.println("Please press the given option inside the [''].");
            }
        }
    }
}
