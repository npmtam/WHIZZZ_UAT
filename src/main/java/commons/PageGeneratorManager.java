package commons;

import org.openqa.selenium.WebDriver;
import pageObject.DiscoverPageObject;
import pageObject.LoginPageObject;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static DiscoverPageObject getDiscoverPage(WebDriver driver){
        return new DiscoverPageObject(driver);
    }

}
