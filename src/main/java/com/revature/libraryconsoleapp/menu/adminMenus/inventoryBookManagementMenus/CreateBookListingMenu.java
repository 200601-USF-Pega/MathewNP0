package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.AuthorService;
import com.revature.libraryconsoleapp.service.CategoryService;
import com.revature.libraryconsoleapp.service.ScannerService;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.Scanner;


public class CreateBookListingMenu implements ISessionMenu {
    private User user;
    private ValidationService validationService = new ValidationService();
    private AuthorService authorService = new AuthorService();
    private CategoryService categoryService = new CategoryService();
    private BookRepoDB bookRepoDB  = new BookRepoDB();
    private CatalogRepoDB inventoryRepoDB = new CatalogRepoDB();
    private Scanner input =  ScannerService.getInstance();

    public CreateBookListingMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {

        //first get all the authors in the database.
        Author author = authorService.authorSelector();
        //get category
        String category = categoryService.categorySelector();
        //System.out.println(category);
        String title = validationService.getValidStringInput("Enter the title for your book: ");
        Book book = new Book(title, author, category);
        int count = validationService.getValidInt("Enter the number of copies: ");
        bookRepoDB.addBook(book);
        int book_id = bookRepoDB.getBookID(book);
        inventoryRepoDB.addInventory(book_id, count);
        System.out.println("added " + count + " to the inventory." );

        System.out.println("Enter any key to go BA.");
        input.nextLine();
        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        sessionMenuFactory.changeMenu("inventory_book_management", user).start();
    }


}
