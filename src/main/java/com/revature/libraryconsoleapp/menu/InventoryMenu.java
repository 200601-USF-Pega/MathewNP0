package com.revature.libraryconsoleapp.menu;

import com.revature.libraryconsoleapp.service.ValidationService;

public class InventoryMenu implements IMenu{
    ValidationService validationService = new ValidationService();
    @Override
    public void start() {
        MenuFactory menuFactory = new MenuFactory();
        IMenu currentMenu;

        //show the inventory
        //ask them if you want to incerease the inventory.

        while (true) {
            String userInput = validationService.getValidStringInput("").toLowerCase();
            switch(userInput) {
                case "1":
                    break;
                default:
                    break;
            }
        }
    }
}
