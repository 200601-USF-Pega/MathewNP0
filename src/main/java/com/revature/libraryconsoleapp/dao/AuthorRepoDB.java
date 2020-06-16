package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//probably needs an interface in the future.
public class AuthorRepoDB {

    public AuthorRepoDB () {}

    public Author addAuthor(Author author){
        try{
            PreparedStatement userStatement = ConnectionService.getInstance().getConnection()
                    .prepareStatement("INSERT INTO Authors(first_name, last_name) VALUES(?,?)");
            userStatement.setString(1, author.getFirstName());
            userStatement.setString(2, author.getLastName());
            userStatement.executeUpdate();
            return author;
        } catch(SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        List<Author> allAuthors = new ArrayList();
        try{
            Statement getAllAuthorStatement = ConnectionService.getInstance().getConnection().createStatement();
            getAllAuthorStatement.executeQuery("SELECT * from Authors");
            ResultSet rs  = getAllAuthorStatement.getResultSet();

            while(rs.next()) {
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                allAuthors.add(author);
            }
            return allAuthors;
        } catch(SQLException e) {
           System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public void updateFirstName(int authorID, String firstName) {
        try{
            Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
            updatePasswordStatement.executeUpdate("UPDATE Authors SET first_name= \"" + firstName+ "\" WHERE author_id= \"" + authorID+"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateLastName(int authorID, String lastName) {
        try{
            Statement updatePasswordStatement = ConnectionService.getInstance().getConnection().createStatement();
            updatePasswordStatement.executeUpdate("UPDATE Authors SET last_name= \"" + lastName+ "\" WHERE author_id= \"" + authorID +"\";");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


