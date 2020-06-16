package com.revature.libraryconsoleapp.menu;

import java.util.Scanner;

public class MainMenu implements IMenu{
    private Scanner input = new Scanner(System.in);
//    private LoginMenu loginMenu = new LoginMenu();
    public void start() {
        String userInput;
        ViewClass.printWelcomeScreen("Console Library App. Version 1.0");
        MenuFactory menuFactory = new MenuFactory();
        IMenu currentMenu;

        while(true){
            System.out.println("[0] Please login: ");
            System.out.println("[1] Exit: ");

            userInput = input.nextLine();

            switch(userInput){
                case "0":
                    currentMenu = menuFactory.changeMenu("login_menu");
                    currentMenu.start();
                    break;
                case "1":
                    System.out.println("Exiting the library application.");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input please try again");
            }


        }
    }

}
