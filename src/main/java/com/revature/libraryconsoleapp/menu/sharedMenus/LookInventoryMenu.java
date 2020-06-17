package com.revature.libraryconsoleapp.menu.sharedMenus;

import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Catalog;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.wagu.Block;
import com.revature.libraryconsoleapp.wagu.Board;
import com.revature.libraryconsoleapp.wagu.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        ViewClass.printSessionHeader("Catalog", user);
        showTable(inventoryService.getCatalogList());

        System.out.println("click any key to BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        if(user.getAccess() == ADMIN) {
            sessionMenuFactory.changeMenu("inventory_book_management", user).start();
        }
        sessionMenuFactory.changeMenu("user_main_menu", user).start();
    }

    private List<List<String>> rowMaker(List<Catalog> catalogList) {
        List<List<String>> listList= new ArrayList<List<String>>();
        int i = 0;
        for(Catalog catalog: catalogList) {
            ArrayList<String> rowList = new ArrayList<>();
            rowList.add(Integer.toString(i));
            rowList.add(catalog.getBook().getTitle());
            rowList.add(catalog.getBook().getAuthor().getFullName());
            rowList.add(catalog.getBook().getCategory());
            rowList.add(Integer.toString(catalog.getAvailableCopies()));
            listList.add(rowList);
            i++;
        }
        return listList;
    }

    private void showTable(List<Catalog> catalogList){
        List<String> headerList = Arrays.asList("No:", "TITLE", "AUTHOR", "CATEGORY", "COPIES");
        List<List<String>> rowsList = rowMaker(catalogList);
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
