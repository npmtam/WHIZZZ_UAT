package pageObject;

import commons.AbstractPage;
import commons.Constants;
import org.openqa.selenium.WebDriver;
import pageUI.LoginUI;

public class LoginPage extends AbstractPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void switchToLoginWindows(){
        switchToWindowsByTitle(Constants.MICROSOFT_LOGIN_TITLE);
    }

    public void clickToLoginButton(){
        waitToElementVisible(LoginUI.LOGIN_BUTTON);
        clickToElement(LoginUI.LOGIN_BUTTON);
    }

    public void inputEmail(String email){
        waitToElementVisible(LoginUI.EMAIL_TEXTBOX);
        sendKeyToElement(LoginUI.EMAIL_TEXTBOX, email);
    }

    public void inputPassword(String password){
        waitToElementVisible(LoginUI.PASSWORD_TEXTBOX);
        sendKeyToElement(LoginUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToSubmitBtn(){
        waitToElementVisible(LoginUI.SUBMIT_BUTTON);
        clickToElement(LoginUI.SUBMIT_BUTTON);
    }

    public void switchBackToWhizzzWindow(){
        switchToWindowsByTitle(Constants.EMMA_WHIZZZ_TITLE);
    }


}
