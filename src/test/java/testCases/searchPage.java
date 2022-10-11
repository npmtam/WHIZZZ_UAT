package testCases;

import com.github.javafaker.Faker;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.DiscoverPage;
import pageObject.LoginPage;

public class searchPage extends AbstractTest {
    private WebDriver driver;
    private String cookie = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJlMWE2OGE3My1mOWRhLTRlMTUtOGJmMy00ODc1MWQ1MGQ1YzkiLCJlbWFpbCI6Im1pbmh0YW0ubmd1eWVuQGVtbWEtc2xlZXAuY29tIiwic3ViIjoxMywiaWF0IjoxNjUyOTUxODI5LCJleHAiOjE2NTU1NDM4Mjl9.Dbo6uKsyfeS7PI9xUc7XxmAu0IyKSS61gpPX7UUV6EA";
    private AbstractPage abstractPage;
    private LoginPage loginPage;
    private DiscoverPage discoverPage;
    private String cardName, cardDescription, nameOfPOC, tag;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName){
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);
        driver.get(Constants.EMMA_WHIZZZ_DEV);
        abstractPage.isElementDisplayed("//button");
//        driver.manage().addCookie(new Cookie("accessToken", cookie));
//        driver.navigate().refresh();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        discoverPage = PageGeneratorManager.getDiscoverPage(driver);
        Faker faker = new Faker();
        cardName = faker.book().title();
        cardDescription = faker.book().publisher();
        nameOfPOC = "Minh Tam Nguyen";
        tag = "selenium";

    }

    @Test
    public void TC01_Login_To_System(){
        log.info("Login to the system");
        loginPage.clickToLoginButton();
        loginPage.switchToLoginWindows();
        loginPage.inputEmail(Constants.EMAIL_WHIZZZ);
        loginPage.clickToSubmitBtn();
        loginPage.inputPassword(Constants.PASSWORD_WHIZZZ);
        loginPage.clickToSubmitBtn();
        loginPage.clickToSubmitBtn();
        loginPage.switchBackToWhizzzWindow();
        verifyTrue(abstractPage.isElementDisplayed("//span[text()='Create New']/ancestor::button"));
        log.info("Bypass the survey if it's displayed");
        discoverPage.clickToSurveyIfAvailable();
    }

    @Test()
    public void TC02_Create_A_Card(){
        log.info("TC02 - Create a new card");
        discoverPage.clickToCreateCard();
        discoverPage.createANewCard(cardName, cardDescription);
        log.info("TC02 - Assign POC");
        discoverPage.assignPOC(nameOfPOC);
        log.info("TC02 - Assign contributor");
        discoverPage.assignContributor("Minh Tam Nguyen");
        log.info("TC02 - Verify Toast Message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_CONTRIBUTOR_UPDATE);
        log.info("TC02 - Add tag");
        discoverPage.addTag(tag);

        log.info("TC02 - Preview the card");
        discoverPage.previewTheCard();
        log.info("TC02 - Verify components in the card preview");
        verifyEquals(discoverPage.getNumberOfContributors(), "+1 contributor");
        verifyEquals(discoverPage.getNameOfPOC(), nameOfPOC);
        verifyEquals(discoverPage.getTagNameDetailsPage(), tag);

        log.info("TC02 - Click to publish the card");
        discoverPage.clickToPublishButton();
        log.info("TC02 - Verify toast message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_CARD_CREATED);
        log.info("TC02 - Verify components in the card published");
        Assert.assertEquals(discoverPage.getNumberOfContributors(), "+1 contributor");
        Assert.assertEquals(discoverPage.getNameOfPOC(), nameOfPOC);
        Assert.assertEquals(discoverPage.getTagNameDetailsPage(), tag);
        Assert.assertTrue(discoverPage.getLastUpdate().contains(discoverPage.getCurrentDate()));
    }
}
