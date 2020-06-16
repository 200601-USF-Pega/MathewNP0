package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.Map;
import java.util.Scanner;

public class EditBookListingMenu implements ISessionMenu {
    private User user;
    private BookRepoDB bookRepoDB = new BookRepoDB();
    private ValidationService validationService = new ValidationService();

    public EditBookListingMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Edit Book", user);
        Map<Integer, Book> bookMap = bookRepoDB.getMappedBookList();
        ViewClass.BookListView(bookMap);

        int userInt = validationService.getValidInt("Enter the number [] for the User.");
        Book selectedBook = bookMap.get(userInt);

        System.out.println("Book" + selectedBook.getTitle() + " selected.");

        System.out.println(
                "Enter any of these options.\n" +
                        "[1] Edit Title\n" +
                        "[2] Edit Author Name\n" +
                        "[3] Edit Category\n" +
                        "[b] BACK."
        );


        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;

        while (true) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    System.out.println("Editing title");
                    break;
                case "2":
                    System.out.println("Editing author name");
                    Author author = selectedBook.getAuthor();
                    currentMenu = sessionMenuFactory.changeMenu("edit_author", user);
                    EditAuthorMenu ed= (EditAuthorMenu)currentMenu;
                    ed.setAuthor(author);
                    ed.setId(userInt);
                    currentMenu.start();
                    break;
                case "3":
                    System.out.println("Editing Category");
                    break;
                case "b":
                    currentMenu = sessionMenuFactory.changeMenu("user_management", user);
                    currentMenu.start();
                    break;
                default:
                    System.out.println("Please press the given option inside the ['']");
            }

        }
    }
}
