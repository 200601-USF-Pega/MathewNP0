package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.*;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogRepoDB {

    public void addInventory(int book_id, int count)  {
        try {
            PreparedStatement bookCopyStatement= ConnectionService.getInstance().getConnection().
                    prepareStatement("INSERT INTO Inventory(book_id) VALUES(?)");
            bookCopyStatement.setString(1, Integer.toString(book_id));
            for (int i =0; i< count; i++) {
                bookCopyStatement.executeUpdate();
            }
        } catch (SQLException e ) {
            System.out.println("Exception: " + e.getMessage());
        }
    }


    public void deleteInventory(int book_id, int count)  {
        try {
            Statement deleteInventoryStatment = ConnectionService.getInstance().getConnection().createStatement();
            deleteInventoryStatment.executeUpdate("DELETE FROM Inventory where book_id =" +book_id+ " LIMIT " + count + ";" );
        } catch (SQLException e ) {
            System.out.println("Exception: " + e.getMessage());
        }
    }




    //get a list of available books.
    //returns book_id  and count
    public Map<Integer, Integer> inventoryAvailable(){
        Map<Integer, Integer> inventoryCount = new HashMap<>();
        try {
            Statement inventoryStatement = ConnectionService.getInstance().getConnection().createStatement();
            inventoryStatement.executeQuery(
                   "SELECT COUNT(book_id) as num, book_id FROM Inventory Group by book_id"
            );
            ResultSet rs = inventoryStatement.getResultSet();
            while (rs.next()) {
                inventoryCount.put(rs.getInt("book_id"), rs.getInt("num"));
            }
            return inventoryCount;

        } catch(SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Catalog> getAllCatalogs() {
        try {
            List<Catalog> catalogList = new ArrayList<>();
            Statement inventoryStatement = ConnectionService.getInstance().getConnection().createStatement();
            inventoryStatement.executeQuery(
                    "SELECT \n" +
                            "\tCOUNT(Inventory.book_id) as copies,\n" +
                            "\tBooks.title,\n" +
                            "\tAuthors.first_name,\n" +
                            "\tAuthors.last_name,\n" +
                            "\tCategory.category\n" +
                            "FROM\n" +
                            "    Books\n" +
                            "JOIN Inventory on Inventory.book_id = Books.book_id\n" +
                            "LEFT JOIN Authors on Authors.author_id = Books.author_id\n" +
                            "LEFT JOIN Category on Category.category_id = Books.category_id\n" +
                            "GROUP BY Inventory.book_id;"
            );
            ResultSet rs = inventoryStatement.getResultSet();
            while (rs.next()) {
                Catalog catalog = new Catalog();

                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(new Author(rs.getString("first_name"),  rs.getString("last_name")));
                book.setCategory(rs.getString("category"));

                catalog.setAvailableCopies(rs.getInt("copies"));
                catalog.setBook(book);
                catalogList.add(catalog);

            }
            return catalogList;
        } catch(SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }



}
