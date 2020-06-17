package com.revature.libraryconsoleapp.menu.adminMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ScannerService;

import java.util.Scanner;

public class OtherActionsMenu implements ISessionMenu {

    private User user;
    private Scanner input = ScannerService.getInstance();

    public OtherActionsMenu(User user){
       this.user = user;
    }
    @Override
    public void start() {
        System.out.println("Other actions coming soon..");

        System.out.println("Enter any key to go BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("user_main_menu", user).start();
    }
}
