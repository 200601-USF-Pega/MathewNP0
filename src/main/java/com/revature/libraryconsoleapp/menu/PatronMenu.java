package com.revature.libraryconsoleapp.menu;


import com.revature.libraryconsoleapp.models.User;

public class PatronMenu implements ISessionMenu {

    private User user;

    public PatronMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("I am a patron.");
    }
}
