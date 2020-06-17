package com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus;

import com.revature.libraryconsoleapp.dao.IUserRepo;
import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
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

public class DeleteUserMenu implements ISessionMenu {
    private User user;
    private ConnectionService connectionService = new ConnectionService();
    private IUserRepo userRepoDB= new UserRepoDB();
    private ValidationService validationService = new ValidationService();

    public DeleteUserMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Delete User Menu", user);


        List<User> users = userRepoDB.getAllUsers();
        //ViewClass.UserListView(users);
        showTable(users);
        int inputIndex = validationService.getValidIntChoice("Enter the number [] which user you to delete", users.size());

        User selectedUser = users.get(inputIndex);
        userRepoDB.deleteUser(selectedUser);
        System.out.println("Deleted the user " + selectedUser.getUserName() + ".");


        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        System.out.println("click any key to go BACK.\n");
        input.nextLine().toLowerCase();
        currentMenu =  sessionMenuFactory.changeMenu("user_management", user);
        currentMenu.start();


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
