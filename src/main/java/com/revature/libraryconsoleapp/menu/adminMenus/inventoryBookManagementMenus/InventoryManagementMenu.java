package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.User;

import java.util.Scanner;

public class InventoryManagementMenu implements ISessionMenu {
    private User user;

    public InventoryManagementMenu(User user) {
       this.user = user;
    }
    @Override

    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Inventory/Book Management", user);
        System.out.println(
                "Enter any of these options.\n" +
                        "[1] Create a Book Listing \n" +
                        "[2] Delete/Add from Catalog\n" +
                        "[3] Edit a Book Listing\n" +
                        "[4] See Available Catalog\n" +
                        "[b] BACK."
        );

        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        while(true) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    currentMenu = sessionMenuFactory.changeMenu("create_book_listing", user);
                    currentMenu.start();
                    break;
                case "2":
                    currentMenu =  sessionMenuFactory.changeMenu("delete_book_listing", user);
                    currentMenu.start();
                    break;
                case "3":
                    currentMenu =  sessionMenuFactory.changeMenu("edit_book_listing", user);
                    currentMenu.start();
                    break;
                case "4":
                    currentMenu =  sessionMenuFactory.changeMenu("look_inventory", user);
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
