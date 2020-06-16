package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepoFile implements IUserRepo{
    private String filepath = "src/main/resources/userTable.txt";

    public User addUser(User user) {
        // TODO Auto-generated method stub
        List<User> currentUsers= this.getAllUsers();
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(filepath));
            currentUsers.add(user);
            objectOutputStream.writeObject(currentUsers);
            objectOutputStream.close();
            return user;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    //finish this method
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        try {
            ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(filepath));
            List<User> retrievedUser= (ArrayList<User>) inputStream.readObject();
            inputStream.close();
            return retrievedUser;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //Just in class Hero class is not found
            e.printStackTrace();
        }

        return new ArrayList<User>();
    }
}
