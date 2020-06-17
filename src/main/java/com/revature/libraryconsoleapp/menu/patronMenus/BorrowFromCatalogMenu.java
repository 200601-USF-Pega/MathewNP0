package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.service.ValidationService;

public class BorrowFromCatalogMenu implements ISessionMenu {
    private User user;
    private InventoryService inventoryService = new InventoryService();
    private ValidationService validationService = new ValidationService();

    public BorrowFromCatalogMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        int size = inventoryService.printInventory();
        System.out.println("Enter the number you to borrow.");
        int userInput = validationService.getValidIntChoice("Enter the number you want to borrow?: ", size);





    }
}
