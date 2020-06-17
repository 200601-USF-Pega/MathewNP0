package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.menu.*;
import com.revature.libraryconsoleapp.models.User;

import java.util.Scanner;

public class PatronMenu implements ISessionMenu {
    private User user;

    public PatronMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("PATRON", user);
        System.out.println(
                "Enter any of these options.\n" +
                        "[1] Look at Catalog \n" +
                        "[2] Borrow from Catalog \n" +
                        "[3] Return a Rental\n" +
                        "[4] History\n" +
                        "[x] LOGOUT."
        );

        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        while(true) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    currentMenu = sessionMenuFactory.changeMenu("look_inventory", user);
                    currentMenu.start();
                    break;
                case "2":
                    currentMenu =  sessionMenuFactory.changeMenu("borrow_from_catalog", user);
                    currentMenu.start();
                    break;
                case "3":
                    currentMenu =  sessionMenuFactory.changeMenu("return rentals", user);
                    currentMenu.start();
                    break;
                case "4":
                    currentMenu =  sessionMenuFactory.changeMenu("history", user);
                    currentMenu.start();
                    break;
                case "x":
                    currentMenu = new MainMenu();
                    currentMenu.start();
                    break;
                default:
                    System.out.println("Please press the given option inside the [''].");
            }
        }
    }
}
