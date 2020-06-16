package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.Access;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepoDB implements IUserRepo{

    //ConnectionService connectionService;

    public UserRepoDB (){}

    @Override
    public User addUser(User user) {
        try {
            PreparedStatement userStatement = ConnectionService.getInstance().getConnection()
                    .prepareStatement(
                    "INSERT INTO Users VALUES (?, ?, ?, ?, ?)");
                    userStatement.setString(1, user.getUserName());
                    userStatement.setString(2, user.getPwd());
                    userStatement.setString(3,  user.getFirstName());
                    userStatement.setString(4,  user.getLastName());
                    userStatement.setString(5, user.getAccess().toString());
                    userStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        String userName = user.getUserName();
        try {
            PreparedStatement deleteStatement = ConnectionService.getInstance().getConnection().
                    prepareStatement(
                           "DELETE FROM Users WHERE user_name = ?");
            deleteStatement.setString(1, userName);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Excepiton: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public boolean doesUserExist(String userName) {
       try {
           Statement userStatement = ConnectionService.getInstance().getConnection().createStatement();
           userStatement.executeQuery("SELECT user_name FROM Users WHERE user_name = \""+userName+"\" ;");
           ResultSet rs = userStatement.getResultSet();
           if (rs.next()) {
               return true;
           } else {
               return false;
           }

       } catch (SQLException e) {
           System.out.println("Excepiton: " + e.getMessage());
           e.printStackTrace();
       }
       return false;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList();
        try {
            Statement userStatement = ConnectionService.getInstance().getConnection().createStatement();
            userStatement.executeQuery("SELECT * from Users;");
            ResultSet rs = userStatement.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("user_name"));
                user.setPwd(rs.getString("pwd"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAccess(Access.valueOf(rs.getString("access")));
                allUsers.add(user);
            }
            return allUsers;

        } catch(SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updatePassword(String userName, String password) {
        try{
           Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
           updatePasswordStatement.executeUpdate("UPDATE Users SET pwd = \"" + password + "\" WHERE user_name = \"" + userName+"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateFirstName(String userName, String firstName) {
        try{
            Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
            updatePasswordStatement.executeUpdate("UPDATE Users SET first_name= \"" + firstName+ "\" WHERE user_name = \"" + userName+"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateLastName(String userName, String lastName) {
        try{
            Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
            updatePasswordStatement.executeUpdate("UPDATE Users SET last_name= \"" + lastName+ "\" WHERE user_name = \"" + userName+"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateAccess(String userName, String access) {
        try{
            Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
            updatePasswordStatement.executeUpdate("UPDATE Users SET access = \"" + access+ "\" WHERE user_name = \"" + userName+"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
