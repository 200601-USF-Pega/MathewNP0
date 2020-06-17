package com.revature.libraryconsoleapp.menu.sharedMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;

import java.util.Scanner;

import static com.revature.libraryconsoleapp.models.Access.ADMIN;

public class LookInventoryMenu implements ISessionMenu {
    private User user;
    private InventoryService inventoryService = new InventoryService();
    private Scanner input = new Scanner(System.in);

    public LookInventoryMenu(User user) {
        this.user = user;
    }

    @Override

    public void start() {
        inventoryService.printInventory();
        System.out.println("click any key to BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        if(user.getAccess() == ADMIN) {
            sessionMenuFactory.changeMenu("inventory_book_management", user).start();
        }
        sessionMenuFactory.changeMenu("user_main_menu", user).start();
    }


}
