package com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus;

import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ConnectionService;
import com.revature.libraryconsoleapp.service.ValidationService;
import com.revature.libraryconsoleapp.wagu.Block;
import com.revature.libraryconsoleapp.wagu.Board;
import com.revature.libraryconsoleapp.wagu.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditUserMenu implements ISessionMenu {
    private User user;
    private ConnectionService connectionService = new ConnectionService();
    private UserRepoDB userRepoDB = new UserRepoDB();
    private ValidationService validationService = new ValidationService();

    public EditUserMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Edit User Menu", user);
        List<User> userList = userRepoDB.getAllUsers();
        showTable(userList);

        int userInt = validationService.getValidIntChoice("Enter the number [] for the User.", userList.size());
        User selectedUser = userList.get(userInt);
        System.out.println("User " + selectedUser.getUserName() + " selected.");
        System.out.println(
                "Enter any of these options.\n" +
                        "[1] Update Password\n" +
                        "[2] Edit First Name\n" +
                        "[3] Edit Last Name\n" +
                        "[4] Edit Access privilege\n" +
                        "[b] BACK."
        );


        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;
        String userName = selectedUser.getUserName();

        while (true) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    String password = validationService.getValidStringInput("Enter the new password: ");
                    userRepoDB.updatePassword(userName, password);
                    System.out.println("Updated password for user: " + userName + ", press b to back.");
                    break;
                case "2":
                    String firstName = validationService.getValidNameInput("Enter the new first name: ");
                    userRepoDB.updateFirstName(userName, firstName);
                    System.out.println("Updated first name for user:  " + userName + ", press b to back.");
                    break;
                case "3":
                    String lastName = validationService.getValidNameInput("Enter the new last name: ");
                    userRepoDB.updateLastName(userName, lastName);
                    System.out.println("Updated last name for user: " + userName + ", press b to back.");
                    break;
                case "4":
                    System.out.println(
                            "Enter\n[1] ADMIN\n" +
                                    "[2] PATRON\n" +
                                    "[3] BANNED"
                    );

                    String accessName = choosePrivilige();
                    userRepoDB.updateAccess(userName, accessName);
                    System.out.println("Updated Access Type for user: " + userName + ", press b to back.");
                    break;
                case "b":
                    currentMenu = sessionMenuFactory.changeMenu("user_management", user);
                    currentMenu.start();
                    break;
                default:
                    System.out.println("Please press the given option inside the [''] ");
            }
        }

    }

    private String choosePrivilige() {
        String accessName = "";
        while (true) {
            String accessInput = Integer.toString(validationService.getValidInt(""));
            switch (accessInput) {
                case "1":
                    accessName = "ADMIN";
                    break;
                case "2":
                    accessName = "PATRON";
                    break;
                case "3":
                    accessName = "BANNED";
                    break;
                default:
                    System.out.println("Please enter a valid option.");
            }
            return accessName;

        }
    }

    private List<List<String>> rowMaker(List<User> userList) {
        List<List<String>> listList= new ArrayList<List<String>>();
        int i = 0;
        for(User user: userList) {
            ArrayList<String> rowList = new ArrayList<>();
            rowList.add(Integer.toString(i));
            rowList.add(user.getUserName());
            rowList.add(user.getPwd());
            rowList.add(user.getFirstName());
            rowList.add(user.getLastName());
            rowList.add(user.getAccess().toString());
            listList.add(rowList);
            i++;
        }
        return listList;
    }

    private void showTable(List<User> users){
        List<String> headerList = Arrays.asList("No:", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME", "ACCESS");
        List<List<String>> rowsList = rowMaker(users);
        Board board = new Board(75);
        Table table = new Table(board, 75, headerList, rowsList);
        //table.setGridMode(Table.GRID_NON);

        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        String tableString = board.getPreview();
        System.out.println(tableString);
    }

}
