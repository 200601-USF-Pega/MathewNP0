package com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus;

import com.revature.libraryconsoleapp.dao.AuthorRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.menu.SessionMenuFactory;
import com.revature.libraryconsoleapp.menu.ViewClass;
import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ValidationService;

import java.util.Scanner;

public class EditAuthorMenu implements ISessionMenu {
    private User user;
    private int id;
    private Author author;
    private AuthorRepoDB authorRepoDB = new AuthorRepoDB();
    private ValidationService validationService = new ValidationService();

    public EditAuthorMenu(User user) {
        this.user = user;
    }

    public void setAuthor(Author author) {
       this.author  = author;
    }
    public void setId(int id) {this.id = id;}

    @Override
    public void start() {


        Scanner input = new Scanner(System.in);
        ViewClass.printSessionHeader("Edit Author", user);
        System.out.println("Options");
        System.out.println("" +
                        "[1] Edit first name\n" +
                        "[2] Edit last name\n" +
                        "[b] Back."
                    );
        System.out.println("Current Author Name: " + author.getFullName());

        chooseAction(input);

        SessionMenuFactory sessionMenuFactory = new SessionMenuFactory();
        IMenu currentMenu;
        System.out.println("Enter any key to back.");
        input.nextLine();
        currentMenu = sessionMenuFactory.changeMenu("inventory_book_management", user);
        currentMenu.start();
        }

        private void chooseAction(Scanner input) {
            String userInput = input.nextLine().toLowerCase();
            switch (userInput) {
                case "1":
                    String firstName = validationService.getValidStringInput("Enter the new first name: ");
                    authorRepoDB.updateFirstName(id, firstName);
                    System.out.println("Updated First Name to " + firstName);
                    break;
                case "2":
                    String lastName= validationService.getValidStringInput("Enter the new last name: ");
                    authorRepoDB.updateLastName(id, lastName);
                    System.out.println("Updated Last Name to " + lastName);
                    break;
                default:
                    System.out.println("Please press the right key.");
                    chooseAction(input);
            }
        }


}

