package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.dao.RentalRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.Catalog;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.InventoryService;
import com.revature.libraryconsoleapp.service.ScannerService;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        int size = inventoryService.printInventory();
        int userInput = validationService.getValidIntChoice("Enter the number you want to borrow?: ", size);
        System.out.println("You entered: " + userInput);
        List<Catalog> catalogList = catalogRepoDB.getAllCatalogs();
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
}
