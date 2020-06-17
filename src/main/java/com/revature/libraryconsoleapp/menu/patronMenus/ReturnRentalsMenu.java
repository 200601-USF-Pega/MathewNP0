package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.dao.RentalRepoDB;
import com.revature.libraryconsoleapp.dao.UserHistoryRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.Catalog;
import com.revature.libraryconsoleapp.models.Rental;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ScannerService;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.List;
import java.util.Scanner;

public class ReturnRentalsMenu implements ISessionMenu {
    private User user;
    private RentalRepoDB rentalRepoDB = new RentalRepoDB();
    private ValidationService validationService = new ValidationService();
    private BookRepoDB bookRepoDB = new BookRepoDB();
    private CatalogRepoDB catalogRepoDB = new CatalogRepoDB();
    private Scanner input = ScannerService.getInstance();
    private UserHistoryRepoDB userHistoryRepoDB= new UserHistoryRepoDB();

    public ReturnRentalsMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
       //see all your rentals.
       List<Rental> rentalList = rentalRepoDB.getAllRentalsForAUser(user);

       for(Rental rental: rentalList) {
           System.out.println(rental);
       }
       //choose from there and make a new inventory_id.

        int userInput = validationService.getValidIntChoice("Enter which book you want to return.: ", rentalList.size());
        System.out.println("You entered: " + userInput);

        Rental selectedRental = rentalList.get(userInput);
        System.out.println(selectedRental);


        int bookid = bookRepoDB.getBookID(selectedRental.getBook());
        catalogRepoDB.addInventory(bookid, 1);
        rentalRepoDB.
                deleteEntry(bookid, user);


        userHistoryRepoDB.addHistory(selectedRental);
        System.out.println("You have returned 1 copy of the book " + selectedRental.getBook().getTitle());

        System.out.println("Enter any key to go BACK.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("user_main_menu", user).start();
    }
}
