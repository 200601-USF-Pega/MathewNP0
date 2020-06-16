package com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus;

import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.util.List;
import java.util.Scanner;

public class ShowAllUsersMenu implements ISessionMenu {
    private User user;
    private ConnectionService connectionService = new ConnectionService();
    private UserRepoDB userRepoDB= new UserRepoDB();

    public ShowAllUsersMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        //needs to have a menu.
        ViewClass.printSessionHeader("All Users", user);
        List<User> users = userRepoDB.getAllUsers();

        //need view?
        ViewClass.UserListView(users);

        Scanner input = new Scanner(System.in);
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        System.out.println("click any key to go BACK.\n");
        input.nextLine().toLowerCase();
        currentMenu =  sessionMenuFactory.changeMenu("user_management", user);
        currentMenu.start();

    }

}
