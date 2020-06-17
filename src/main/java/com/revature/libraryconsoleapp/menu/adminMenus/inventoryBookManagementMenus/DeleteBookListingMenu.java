package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.Catalog;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.service.ScannerService;
import com.revature.libraryconsoleapp.service.ValidationService;
import com.revature.libraryconsoleapp.wagu.Block;
import com.revature.libraryconsoleapp.wagu.Board;
import com.revature.libraryconsoleapp.wagu.Table;

import java.util.*;

public class DeleteBookListingMenu implements ISessionMenu {
    private User user;
    private InventoryService inventoryService = new InventoryService();
    private Scanner input = ScannerService.getInstance();
    private ValidationService validationService = new ValidationService();
    private CatalogRepoDB catalogRepoDB = new CatalogRepoDB();
    private BookRepoDB bookRepoDB = new BookRepoDB();
    public DeleteBookListingMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        ViewClass.printSessionHeader("Add/Delete Catalog.", user);
        List<Book> bookListing = bookRepoDB.getAllBooks();
        //ViewClass.UserListView(bookListing);

        System.out.println();
        System.out.println();

        List<Catalog> catalogList= catalogRepoDB.getAllCatalogs();
        //ViewClass.UserListView(catalogList);
        showTable(catalogList);
        System.out.println("click any key to BACK.");
        int inputIndex = validationService.getValidIntChoice("Enter the number[] which catalog you want to edit: ", catalogList.size());
        int count = validationService.getValidInt("Enter the number of copies you wanted deleted:  ");
        Catalog catalog = catalogList.get(inputIndex);

        int bookID = bookRepoDB.getBookID(catalog.getBook());
        catalogRepoDB.deleteInventory(bookID, count);


       System.out.println("Click any button to BACK>");
       input.nextLine();


        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("inventory_book_management", user).start();
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
