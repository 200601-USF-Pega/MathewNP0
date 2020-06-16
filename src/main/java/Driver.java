import com.revature.libraryconsoleapp.dao.UserRepoDB;
import com.revature.libraryconsoleapp.menu.IMenu;
import com.revature.libraryconsoleapp.menu.MainMenu;
import com.revature.libraryconsoleapp.service.BootstrapService;

public class Driver {

    public static void main(String[] args) {

        BootstrapService bootstrapService = new BootstrapService(new UserRepoDB());
        bootstrapService.checkForAdmin();

        IMenu currentMenu = new MainMenu();
        currentMenu.start();
    }
}
