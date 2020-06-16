package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.service.ScannerService;

import java.util.Scanner;

public class DeleteBookListingMenu implements ISessionMenu {
    private User user;
    private InventoryService inventoryService = new InventoryService();
    private Scanner input = ScannerService.getInstance();
    public DeleteBookListingMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        ViewClass.printSessionHeader("Delete Book Listing", user);
        inventoryService.getInventory();
        System.out.println("Available books to rent");

        System.out.println("click any key to BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("inventory_book_management", user).start();
    }
}
