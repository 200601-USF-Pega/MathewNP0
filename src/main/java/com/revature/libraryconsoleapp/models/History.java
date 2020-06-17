package com.revature.libraryconsoleapp.models;

public class History {
    private String endDate;
    private Rental rental;

    public History() {
    }

    public History(String endDate, Rental rental) {
        this.endDate = endDate;
        this.rental = rental;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String toString() {
        return "History{" +
                "endDate='" + endDate + '\'' +
                ", rental=" + rental.toString() +
                '}';
    }
}
