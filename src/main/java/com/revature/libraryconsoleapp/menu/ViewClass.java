package com.revature.libraryconsoleapp.menu;

import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.User;

import java.util.List;
import java.util.Map;

public class ViewClass {

    public static void printSessionHeader(String menuName, User user) {
        System.out.println("===========================================================");
        System.out.println(menuName +" Menu." + " Current User: " + user.getUserName());
        System.out.println("===========================================================");
    }

    public static void printSessionHeader(String menuName) {
        System.out.println("===========================================================");
        System.out.println(menuName +" Menu.");
        System.out.println("===========================================================");
    }

    public static void printWelcomeScreen(String prompt) {
        System.out.println("===========================================================");
        System.out.println(prompt);
        System.out.println("===========================================================");
    }


    public static <T>void UserListView(List<T> itemList){
        int index = 0;
        for(T item: itemList) {
            System.out.println("No:" + index + "   " + item.toString());
            index++;
        }
    }

    public static void BookListView(Map<Integer, Book> bookMap){
        for(Map.Entry<Integer, Book> entry :bookMap.entrySet()) {
            System.out.println("No:" + entry.getKey()+ ":" + entry.getValue().toString());
        }
    }


}
