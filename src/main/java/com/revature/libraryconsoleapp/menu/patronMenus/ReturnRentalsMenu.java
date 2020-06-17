package com.revature.libraryconsoleapp.menu.patronMenus;

import com.revature.libraryconsoleapp.dao.RentalRepoDB;
import com.revature.libraryconsoleapp.menu.ISessionMenu;
import com.revature.libraryconsoleapp.models.Rental;
import com.revature.libraryconsoleapp.models.User;

import java.util.List;

public class ReturnRentalsMenu implements ISessionMenu {
    private User user;
    private RentalRepoDB rentalRepoDB = new RentalRepoDB();

    public ReturnRentalsMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
       //see all your rentals.
       List<Rental> rentalList = rentalRepoDB.getAllRentalsForAUser(user);

       for(Rental rental: rentalList) {
           System.out.println(rental);
       }
       //choose from there and make a new inventory_id.
    }
}
