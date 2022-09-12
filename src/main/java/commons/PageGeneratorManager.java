package commons;

import org.openqa.selenium.WebDriver;
import pageObject.DiscoverPage;
import pageObject.LoginPage;

public class PageGeneratorManager {

    public static LoginPage getLoginPage(WebDriver driver){
        return new LoginPage(driver);
    }

    public static DiscoverPage getDiscoverPage(WebDriver driver){
        return new DiscoverPage(driver);
    }

}
