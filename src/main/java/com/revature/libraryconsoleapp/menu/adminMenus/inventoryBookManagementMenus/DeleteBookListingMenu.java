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

import java.util.List;
import java.util.Scanner;

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
        ViewClass.UserListView(bookListing);

        System.out.println();
        System.out.println();

        List<Catalog> catalogList= catalogRepoDB.getAllCatalogs();
        ViewClass.UserListView(catalogList);
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
}
