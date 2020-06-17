package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.dao.RentalRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Catalog;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.service.ScannerService;
import com.revature.libraryconsoleapp.service.ValidationService;
import com.revature.libraryconsoleapp.wagu.Block;
import com.revature.libraryconsoleapp.wagu.Board;
import com.revature.libraryconsoleapp.wagu.Table;

import java.util.*;

public class BorrowFromCatalogMenu implements ISessionMenu {
    private User user;
    private InventoryService inventoryService = new InventoryService();
    private ValidationService validationService = new ValidationService();
    private CatalogRepoDB catalogRepoDB = new CatalogRepoDB();
    private BookRepoDB bookRepoDB = new BookRepoDB();
    private RentalRepoDB rentalRepoDB = new RentalRepoDB();
    private Scanner input = ScannerService.getInstance();

    public BorrowFromCatalogMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        ViewClass.printSessionHeader("Borrow Books", user);
        List<Catalog> catalogList = inventoryService.getCatalogList();
        showTable(catalogList);
        int size = catalogList.size();
        int userInput = validationService.getValidIntChoice("Enter the number you want to borrow?: ", size);
        System.out.println("You entered: " + userInput);
        Catalog selectedCatalog = catalogList.get(userInput);
        System.out.println(selectedCatalog);
        int bookid = bookRepoDB.getBookID(selectedCatalog.getBook());
        catalogRepoDB.deleteInventory(bookid, 1);
        rentalRepoDB.addEntry(user, bookid);

        System.out.println("You have borrowed 1 copy of the book " + selectedCatalog.getBook().getTitle());

        System.out.println("Enter any key to go BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
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
