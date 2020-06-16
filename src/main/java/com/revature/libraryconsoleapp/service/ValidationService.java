package com.revature.libraryconsoleapp.service;

import java.util.Scanner;

public class ValidationService {
    Scanner input = new Scanner(System.in);
    boolean invalid = true;

    public String getValidStringInput(String prompt) {
        String userInput;
        do {
            System.out.print(prompt);
            userInput = input.nextLine();
            if(!userInput.isEmpty()) break;
            System.out.println("Please input non empty string");
        } while(invalid);
        return userInput;
    }

    public String getValidNameInput(String prompt) {
        String userInput;
        do {
            System.out.print(prompt);
            userInput = input.nextLine();
            if(userInput.isEmpty()) {
                System.out.println("non empty string cannot be an input..");
            } else {
                boolean isnotLetter = false;
                char[] chars = userInput.toCharArray();
                for(char c: chars) {
                    if(!Character.isLetter(c)) {
                        isnotLetter = true;
                    }
                }
                if(isnotLetter == false) {
                    break;
                } else {
                    System.out.println("Enter a name with ONLY VALID LETTERS.");
                }
            }
        } while(invalid);
        return userInput;
    }


    public int getValidInt(String prompt) {
        int userInput = 0;
        do {
            System.out.println(prompt);
            try {
                userInput = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Please input valid integer");
            }
        } while(invalid);
        return userInput;
    }

    public boolean getValidBoolean(String prompt) {
        invalid = true;
        String userInput;
        do {
            System.out.println(prompt);
            userInput = input.next();
            if(userInput.equalsIgnoreCase("true") || userInput.equalsIgnoreCase("false")) break;
            System.out.println("Please input either true or false");
        } while(invalid);
        return Boolean.parseBoolean(userInput);

    }

    public boolean trueIfNotHappy (int input , int size){
        if (input > size || input <0){
            System.out.println("Please enter a valid option");
            return true;
        }
        return false;
    }

    public int getValidIntChoice(String prompt, int size) {
        int userInput = 0;
        do {
            System.out.println(prompt);
            try {
                userInput = Integer.parseInt(input.nextLine());
                if(trueIfNotHappy(userInput, size-1)) {
                    continue;
                } else {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please input valid integer");
            }
        } while(invalid);
        return userInput;
    }

}
