import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.MainMenu;
import com.revature.libraryconsoleapp.service.BootstrapService;
import org.apache.log4j.PropertyConfigurator;

import java.util.logging.Logger;

public class Driver {

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");

        BootstrapService bootstrapService = new BootstrapService(new UserRepoDB());
        bootstrapService.checkForAdmin();

        IMenu currentMenu = new MainMenu();
        currentMenu.start();
    }
}
