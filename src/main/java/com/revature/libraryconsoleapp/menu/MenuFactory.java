package com.revature.libraryconsoleapp.menu;

//jacob harrington's idea
public class MenuFactory {

    public MenuFactory() {}
    public IMenu changeMenu(String menuName) {
        IMenu newMenu = null;
        switch (menuName) {
            case "main_menu":
               newMenu =  new MainMenu();
               break;
            case "login_menu":
               newMenu = new LoginMenu();
                break;
        }
        return newMenu;
    }
}
