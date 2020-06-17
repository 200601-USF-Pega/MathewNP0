package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.Rental;
import com.revature.libraryconsoleapp.models.User;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalRepoDB {

    public User addEntry(User user, int book_id) {
        try {
            PreparedStatement userStatement = ConnectionService.getInstance().getConnection()
                    .prepareStatement(
                            "INSERT INTO Rentals(user_name, book_id) VALUES (?, ?)");
            userStatement.setString(1, user.getUserName());
            userStatement.setString(2, Integer.toString(book_id));
            userStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteEntry(int book_id, User user)  {
        try {
            Statement deleteInventoryStatment = ConnectionService.getInstance().getConnection().createStatement();
            deleteInventoryStatment.executeUpdate("DELETE FROM Rentals where user_name = '"+user.getUserName()+"' and book_id = '"+book_id+"';\n");
        } catch (SQLException e ) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public List<Rental> getAllRentalsForAUser(User user) {
        List<Rental> rentalList = new ArrayList<>();
        try {
            Statement getAllRentalStatement = ConnectionService.getInstance().getConnection().createStatement();
            getAllRentalStatement.executeQuery(
                    "SELECT \n" +
                            "\tRentals.user_name,\n" +
                            "\tRentals.start_date,\n" +
                            "    Books.title,\n" +
                            "    Authors.first_name,\n" +
                            "    Authors.last_name,\n" +
                            "    Category.category\n" +
                            "FROM\n" +
                            "\tRentals\n" +
                            "INNER JOIN Books ON Rentals.book_id = Books.book_id\n" +
                            "INNER JOIN Authors ON Authors.author_id = Books.author_id\n" +
                            "INNER JOIN Category ON Category.category_id = Books.author_id\n" +
                            "WHERE user_name = \"" + user.getUserName() + "\";"
            );
            ResultSet rs = getAllRentalStatement.getResultSet();

            while (rs.next()) {
                Rental rental = new Rental();
                rental.setUser(user);
                rental.setStartDate(rs.getString("start_date"));
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(new Author(rs.getString("first_name"), rs.getString("last_name")));
                book.setCategory(rs.getString("category"));
                rental.setBook(book);
                rentalList.add(rental);
            }
            return rentalList;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;

    }
}
