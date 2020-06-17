package com.revature.libraryconsoleapp.dao;


import com.revature.libraryconsoleapp.models.*;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserHistoryRepoDB {

    //needs to be put in a service?
    private BookRepoDB bookRepoDB = new BookRepoDB();
    public User addHistory(Rental rental) {
        try {
            PreparedStatement userStatement = ConnectionService.getInstance().getConnection()
                    .prepareStatement(
                            "INSERT INTO HISTORY(start_date, book_id, user_name) VALUES (?, ?, ?)");
            int bookID = bookRepoDB.getBookID(rental.getBook());
            userStatement.setString(1, rental.getStartDate());
            userStatement.setString(2, Integer.toString(bookID));
            userStatement.setString(3,  rental.getUser().getUserName());
            userStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<History> getAllHistory(User user) {
        List<History> historyList = new ArrayList<>();
        try {
            Statement userStatement = ConnectionService.getInstance().getConnection().createStatement();
            userStatement.executeQuery(
                    "SELECT\n" +
                            "    HISTORY.start_date,\n" +
                            "    HISTORY.end_date,\n" +
                            "    HISTORY.user_name,\n" +
                            "\t\n" +
                            "    Authors.first_name,\n" +
                            "    Authors.last_name,\n" +
                            "\t\n" +
                            "    Books.title,\n" +
                            "    Category.category\n" +
                            "FROM\n" +
                            "\tHISTORY\n" +
                            "JOIN Books on Books.book_id = HISTORY.book_id\n" +
                            "LEFT JOIN Authors on Authors.author_id = Books.author_id\n" +
                            "LEFT JOIN Category on Category.category_id = Books.category_id\n" +
                            "WHERE\n" +
                            "\tuser_name = \""+user.getUserName()+"\";"
            );
            ResultSet rs = userStatement.getResultSet();
            while (rs.next()) {
                History history = new History();
                Rental rental = new Rental();
                Book book = new Book();

                book.setTitle(rs.getString("title"));
                book.setAuthor(new Author(rs.getString("first_name"), rs.getString("last_name") ));
                book.setCategory(rs.getString("category"));

                rental.setUser(user);
                rental.setBook(book);

                rental.setStartDate(rs.getString("start_date"));
                history.setEndDate(rs.getString("end_date"));
                history.setRental(rental);

                historyList.add(history);

            }
            return historyList;

        } catch(SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;

    }


}
