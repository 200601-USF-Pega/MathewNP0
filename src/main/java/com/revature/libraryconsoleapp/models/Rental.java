package com.revature.libraryconsoleapp.models;

public class Rental {

    private User user;
    private String startDate;
    private Book book;

    public Rental() {

    }
    public Rental(User user, String startDate, Book book) {
        this.user = user;
        this.startDate = startDate;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "user=" + user.getUserName() +
                ", startDate='" + startDate + '\'' +
                ", book=" + book.getTitle() +
                '}';
    }
}