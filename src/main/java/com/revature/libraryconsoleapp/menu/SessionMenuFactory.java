package com.revature.libraryconsoleapp.menu;

import com.revature.libraryconsoleapp.menu.adminMenus.AdminMenu;
import com.revature.libraryconsoleapp.menu.adminMenus.BannedMenu;
import com.revature.libraryconsoleapp.menu.adminMenus.inventoryBookManagementMenus.*;
import com.revature.libraryconsoleapp.menu.adminMenus.OtherActionsMenu;
import com.revature.libraryconsoleapp.menu.adminMenus.userManagementMenus.*;
import com.revature.libraryconsoleapp.menu.patronMenus.BorrowFromCatalogMenu;
import com.revature.libraryconsoleapp.menu.patronMenus.PatronMenu;
import com.revature.libraryconsoleapp.menu.sharedMenus.LookInventoryMenu;
import com.revature.libraryconsoleapp.models.User;

public class SessionMenuFactory {

    private ISessionMenu accessManager(User user) {
      ISessionMenu sessionMenu = null;

      switch (user.getAccess()) {
          case ADMIN:
              sessionMenu = new AdminMenu(user);
              break;

          case PATRON:
              sessionMenu = new PatronMenu(user);
              break;

          case BANNED:
              sessionMenu = new BannedMenu(user);
              break;
      }
      return sessionMenu;
    }

   public ISessionMenu changeMenu(String menuName, User user) {
        ISessionMenu newMenu = null;
        switch(menuName) {
            case "user_main_menu":
                newMenu = accessManager(user);
                break;
            case "user_management":
                newMenu = new UserManagementMenu(user);
                break;
            case "inventory_book_management":
                newMenu = new InventoryManagementMenu(user);
                break;
            case "other_actions":
                newMenu = new OtherActionsMenu(user);
                break;

            case "create_user_menu":
                newMenu = new CreateUserMenu(user);
                break;
            case "delete_user_menu":
                newMenu = new DeleteUserMenu(user);
                break;
            case "edit_a_user":
                newMenu = new EditUserMenu(user);
                break;
            case "look_at_users":
                newMenu = new ShowAllUsersMenu(user);
                break;

            case  "create_book_listing":
                newMenu = new CreateBookListingMenu(user);
                break;
            case "delete_book_listing":
                newMenu = new DeleteBookListingMenu(user);
                break;
            case "edit_author":
                newMenu = new EditAuthorMenu(user);
                break;
            case "edit_book_listing":
                newMenu = new EditBookListingMenu(user);
                break;
            case  "look_inventory":
                newMenu = new LookInventoryMenu(user);
                break;
            case "borrow_from_catalog":
                newMenu = new BorrowFromCatalogMenu(user);
                break;

        }


       return newMenu;
   }

}
