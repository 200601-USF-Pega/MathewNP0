package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.dao.UserHistoryRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.History;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ScannerService;

import java.util.List;
import java.util.Scanner;

public class UserHistoryMenu implements ISessionMenu {
    private User user;
    private UserHistoryRepoDB userHistoryRepoDB = new UserHistoryRepoDB();
    private Scanner input = ScannerService.getInstance();

    public UserHistoryMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        ViewClass.printSessionHeader("User History", user);
        List<History> userHistory = userHistoryRepoDB.getAllHistory(user);
        for (History history: userHistory) {
            System.out.println(history.toString());
        }

        System.out.println("Enter any key to go BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("user_main_menu", user).start();
    }
}
