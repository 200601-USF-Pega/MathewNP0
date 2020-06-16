package com.revature.libraryconsoleapp.models;

public class Catalog {
    private Book book;
    private int availableCopies;

    public Catalog() {

    }

    public Catalog(Book book, int availableCopies) {
        this.book = book;
        this.availableCopies = availableCopies;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "book=" + book.toString() +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
