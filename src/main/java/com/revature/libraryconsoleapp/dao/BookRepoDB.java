package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.Author;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.service.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepoDB {
    public BookRepoDB() {}

    public Book addBook(Book book) {
        try {
            int author_id = getAuthorID(book.getAuthor().getFirstName());
            //System.out.println(author_id);
            int category_id = getCategoryID(book.getCategory());
            //System.out.println(category_id);
            PreparedStatement bookStatement = ConnectionService.getInstance().getConnection()
                    .prepareStatement("INSERT INTO Books(title, author_id, category_id) VALUES(?, ?, ?)");
            bookStatement.setString(1, book.getTitle());
            bookStatement.setString(2, Integer.toString(author_id));
            bookStatement.setString(3, Integer.toString(category_id));
            bookStatement.executeUpdate();
            return book;

        } catch (SQLException e) {
            System.out.println("Exception; " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private int getAuthorID(String firstName){
        int authorID = -1;
        try{
            Statement authIDStatement = ConnectionService.getInstance().getConnection().createStatement();
            authIDStatement.executeQuery("SELECT author_id from Authors where first_name =\""+firstName+"\";");
            ResultSet rs = authIDStatement.getResultSet();
            while(rs.next()){
                authorID = Integer.parseInt(rs.getString("author_id"));
            }
            return authorID;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return authorID;
    }

    public int getBookID(Book book){
        int bookID = -1;
        try{
            Statement authIDStatement = ConnectionService.getInstance().getConnection().createStatement();
            authIDStatement.executeQuery("SELECT book_id from Books where title=\""+book.getTitle()+"\";");
            ResultSet rs = authIDStatement.getResultSet();
            while(rs.next()){
                bookID= Integer.parseInt(rs.getString("book_id"));
            }
            return bookID;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return bookID;
    }
    private int getCategoryID(String category){
        int categoryID= -1;
        try{
            Statement categoryIDStatement= ConnectionService.getInstance().getConnection().createStatement();
            categoryIDStatement.executeQuery("SELECT category_id from Category where category=\""+category+"\";");
            ResultSet rs = categoryIDStatement.getResultSet();
            while(rs.next()){
                categoryID= Integer.parseInt(rs.getString("category_id"));
            }
            return categoryID;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return categoryID;
    }


    public List<Book> getAllBooks() {
        List <Book> bookList= new ArrayList<>();
        try{
            Statement getAllBookStatement = ConnectionService.getInstance().getConnection().createStatement();
            getAllBookStatement.executeQuery("select Books.book_id, Books.title, Authors.first_name, Authors.last_name, Category.category\n" +
                    "from Books\n" +
                    "INNER JOIN Authors on Books.author_id = Authors.author_id \n" +
                    "INNER JOIN Category on Books.category_id = Category.category_id;");
            ResultSet rs = getAllBookStatement.getResultSet();

            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(new Author(rs.getString("first_name"), rs.getString("last_name")));
                book.setCategory(rs.getString("category"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return null;

    }
   //maps the book_id and book.
    public Map<Integer, Book> getMappedBookList() {
        Map<Integer, Book> bookIDMap = new HashMap<>();
        try{
           Statement getAllBookStatement = ConnectionService.getInstance().getConnection().createStatement();
           getAllBookStatement.executeQuery("select Books.book_id, Books.title, Authors.first_name, Authors.last_name, Category.category\n" +
                   "from Books\n" +
                   "INNER JOIN Authors on Books.author_id = Authors.author_id \n" +
                   "INNER JOIN Category on Books.category_id = Category.category_id;");
           ResultSet rs = getAllBookStatement.getResultSet();

           while (rs.next()) {
               Book book = new Book();
               book.setTitle(rs.getString("title"));
               book.setAuthor(new Author(rs.getString("first_name"), rs.getString("last_name")));
               book.setCategory(rs.getString("category"));
               int book_id = Integer.parseInt(rs.getString("book_id"));
               bookIDMap.put(book_id, book);
           }
           return bookIDMap;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }


}
