package com.revature.libraryconsoleapp.menu.adminMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.MainMenu;
import com.revature.libraryconsoleapp.menu.MenuFactory;
import com.revature.libraryconsoleapp.models.User;

public class BannedMenu implements ISessionMenu {
    private User user;
    public BannedMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("You the user: " + user.getUserName() + " is banned, please contact the admin to resolve this issue.");
        System.out.println();

        new MainMenu().start();
    }
}
