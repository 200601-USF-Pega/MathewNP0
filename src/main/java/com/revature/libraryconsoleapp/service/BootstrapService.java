package com.revature.libraryconsoleapp.service;

import com.revature.libraryconsoleapp.dao.IUserRepo;
import com.revature.libraryconsoleapp.models.Access;
import com.revature.libraryconsoleapp.models.User;

import java.util.List;

//this service checks the database, to see if there is an admin user, if not makes one.
public class BootstrapService {
    IUserRepo repo;
    public BootstrapService(IUserRepo repo) {this.repo = repo;}

    public void checkForAdmin() {
        List<User> retrievedUsers =  repo.getAllUsers();
        if(retrievedUsers.size() == 0) {
            System.out.println("Application fresh start");
            System.out.println("Admin being created....");
            System.out.println("username: admin, password: admin");
            System.out.println("You may change the admin password once logged in.");
            User user1 = new User("admin", "admin", "Mary" ,"Sue", Access.ADMIN);
            repo.addUser(user1);
        } else {
            User user = retrievedUsers.get(0);
            //System.out.println("Username " + user.getUserName() + " found.");
            System.out.println("Bootstrap completed.");
        }

    }


}
