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
    private AbstractPage abstractPage;
    private LoginPage loginPage;
    private DiscoverPage discoverPage;
    private String cardName, cardDescription, tag;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName){
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);
        driver.get(Constants.EMMA_WHIZZZ_DEV);
        abstractPage.isElementDisplayed("//button");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        discoverPage = PageGeneratorManager.getDiscoverPage(driver);
        Faker faker = new Faker();
        cardName = faker.book().title();
        cardDescription = faker.book().publisher();
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
        log.info("TC02 - Input link preview");
        discoverPage.inputLinkPreviewToDescription(Constants.LINK_PREVIEW);
        log.info("TC02 - Select review interval");
        discoverPage.selectReviewInterval("Every month");
        log.info("TC02 - Assign POC");
        discoverPage.assignPOC(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC02 - Assign contributor");
        discoverPage.assignContributor(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC02 - Verify Toast Message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_CONTRIBUTOR_UPDATE);
        log.info("TC02 - Add tag");
        discoverPage.addTag(tag);

        log.info("TC02 - Preview the card");
        discoverPage.previewTheCard();
        log.info("TC02 - Verify components in the card preview");
        verifyEquals(discoverPage.getNumberOfContributors(), "+1 contributor");
        verifyEquals(discoverPage.getNameOfPOC(), Constants.POC_OR_CONTRIBUTOR_NAME);
        verifyEquals(discoverPage.getTagNameDetailsPage(), tag);
        verifyTrue(discoverPage.isLinkPreviewDisplayed());

        log.info("TC02 - Click to publish the card");
        discoverPage.clickToPublishButton();
        log.info("TC02 - Verify toast message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_CARD_CREATED);
        log.info("TC02 - Verify components in the card published");
        Assert.assertEquals(discoverPage.getNumberOfContributors(), "+1 contributor");
        Assert.assertEquals(discoverPage.getNameOfPOC(), Constants.POC_OR_CONTRIBUTOR_NAME);
        Assert.assertEquals(discoverPage.getTagNameDetailsPage(), tag);
        Assert.assertTrue(discoverPage.getLastUpdate().contains(discoverPage.getCurrentDate()));
        Assert.assertTrue(discoverPage.isLinkPreviewDisplayed());
    }

    @Test
    public void TC03_Create_A_Board(){
        discoverPage.backToHomePage();
        log.info("TC03 - Create a new board");
        discoverPage.clickToCreateBoard();
        log.info("TC03 - Input board title and board description");
        discoverPage.createANewCard(cardName, cardDescription);
        log.info("TC03 - Input link preview");
        discoverPage.inputLinkPreviewToDescription(Constants.LINK_PREVIEW);
        log.info("TC03 - Assign contributor");
        discoverPage.assignContributor(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC03 - Verify Toast Message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_CONTRIBUTOR_UPDATE);
        log.info("TC03 - Add cards to board");
        discoverPage.clickToAddCardsButton();
        discoverPage.selectFirstThreeCards();
        discoverPage.clickToAddCardSelected();

        log.info("TC03 - Publish board");
        discoverPage.clickToPublishButton();

        log.info("TC03 - Verify the selected cards added to board");
        Assert.assertEquals(discoverPage.getTitleFirstCardSelected(), Constants.FIRST_CARD_TITLE_SELECTED);
        System.out.println(discoverPage.getTitleSecondCardSelected());
        Assert.assertEquals(discoverPage.getTitleSecondCardSelected(), Constants.SENCOND_CARD_TITLE_SELECTED);
        System.out.println(discoverPage.getTitleSecondCardSelected());
        Assert.assertEquals(discoverPage.getTitleThirdCardSelected(), Constants.THIRD_CARD_TITLE_SELECTED);
        System.out.println(discoverPage.getTitleThirdCardSelected());
        log.info("TC03 - Verify Toast Message");
        verifyEquals(discoverPage.getToastMessage(), Constants.TOAST_MSG_BOARD_CREATED);
        log.info("TC03 - Verify board components");
        verifyTrue(discoverPage.isLinkPreviewDisplayed());
        Assert.assertEquals(discoverPage.getNumberOfContributors(), "+1 contributor");
        Assert.assertTrue(discoverPage.getLastUpdate().contains(discoverPage.getCurrentDate()));
    }
}
