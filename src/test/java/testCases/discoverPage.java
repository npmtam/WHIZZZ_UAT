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
import pageObject.DiscoverPageObject;
import pageObject.LoginPageObject;

public class discoverPage extends AbstractTest {
    private WebDriver driver;
    private AbstractPage abstractPage;
    private LoginPageObject loginPage;
    private DiscoverPageObject discoverPageObject;
    private String cardName, cardDescription, tag;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName){
        driver = getBrowserDriver(browserName);
        abstractPage = new AbstractPage(driver);
        driver.get(Constants.EMMA_WHIZZZ_DEV);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        discoverPageObject = PageGeneratorManager.getDiscoverPage(driver);
        Faker faker = new Faker();
        cardName = faker.book().title();
        cardDescription = faker.book().publisher();
        tag = "selenium";
    }

    @Test
    public void TC01_Login_To_System(){
        log.info("Login to the system");
        loginPage.inputEmail(discoverPageObject.getAccount("email"));
        loginPage.clickToSubmitBtn();
        loginPage.inputPassword(discoverPageObject.getAccount("password"));
        loginPage.clickToSubmitBtn();
        loginPage.clickToSubmitBtn();
        loginPage.clickToLoginButton();
        verifyTrue(abstractPage.isElementDisplayed("//span[text()='Create New']/ancestor::button"));
        log.info("Bypass the survey if it's displayed");
        discoverPageObject.clickToSurveyIfAvailable();
    }

    @Test()
    public void TC02_Create_A_Card(){
        log.info("TC02 - Create a new card");
        discoverPageObject.clickToCreateCard();
        discoverPageObject.createANewCard(cardName, cardDescription);
        log.info("TC02 - Input link preview");
        discoverPageObject.inputLinkPreviewToDescription(Constants.LINK_PREVIEW);
        log.info("TC02 - Select review interval");
        discoverPageObject.selectReviewInterval("Every month");
        log.info("TC02 - Assign POC");
        discoverPageObject.assignPOC(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC02 - Assign contributor");
        discoverPageObject.assignContributor(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC02 - Verify Toast Message");
        verifyEquals(discoverPageObject.getToastMessage(), Constants.TOAST_MSG_CONTRIBUTOR_UPDATE);
        log.info("TC02 - Add tag");
        discoverPageObject.addTag(tag);

        log.info("TC02 - Preview the card");
        discoverPageObject.previewTheCard();
        log.info("TC02 - Verify components in the card preview");
        verifyEquals(discoverPageObject.getNumberOfContributors(), "+1 contributor");
        verifyEquals(discoverPageObject.getNameOfPOC(), Constants.POC_OR_CONTRIBUTOR_NAME);
        verifyEquals(discoverPageObject.getTagNameDetailsPage(), tag);
        verifyTrue(discoverPageObject.isLinkPreviewDisplayed());

        log.info("TC02 - Click to publish the card");
        discoverPageObject.clickToPublishButton();
        log.info("TC02 - Verify gamification toast message");
        verifyEquals(discoverPageObject.getGamificationToastMsg(), Constants.TOAST_MSG_CARD_CREATED);
        log.info("TC02 - Verify components in the card published");
        Assert.assertEquals(discoverPageObject.getNumberOfContributors(), "+1 contributor");
        Assert.assertEquals(discoverPageObject.getNameOfPOC(), Constants.POC_OR_CONTRIBUTOR_NAME);
        Assert.assertEquals(discoverPageObject.getTagNamePublishedPage(), tag);
        Assert.assertTrue(discoverPageObject.getLastUpdate().contains(discoverPageObject.getCurrentDate()));
        Assert.assertTrue(discoverPageObject.isLinkPreviewDisplayed());
    }

    @Test
    public void TC03_Create_A_Board(){
        discoverPageObject.backToHomePage();
        log.info("TC03 - Create a new board");
        discoverPageObject.clickToCreateBoard();
        log.info("TC03 - Input board title and board description");
        discoverPageObject.createANewCard(cardName, cardDescription);
        log.info("TC03 - Input link preview");
        discoverPageObject.inputLinkPreviewToDescription(Constants.LINK_PREVIEW);
        log.info("TC03 - Assign contributor");
        discoverPageObject.assignContributor(Constants.POC_OR_CONTRIBUTOR_NAME);
        log.info("TC03 - Verify Toast Message");
        verifyEquals(discoverPageObject.getToastMessage(), Constants.TOAST_MSG_CONTRIBUTOR_UPDATE);
        log.info("TC03 - Add cards to board");
        discoverPageObject.clickToAddCardsButton();
        discoverPageObject.selectFirstThreeCards();
        discoverPageObject.clickToAddCardSelected();

        log.info("TC03 - Publish board");
        discoverPageObject.clickToPublishButton();

        log.info("TC03 - Verify the selected cards added to board");
        Assert.assertEquals(discoverPageObject.getTitleFirstCardSelected(), Constants.FIRST_CARD_TITLE_SELECTED);
        System.out.println(discoverPageObject.getTitleSecondCardSelected());
        Assert.assertEquals(discoverPageObject.getTitleSecondCardSelected(), Constants.SENCOND_CARD_TITLE_SELECTED);
        System.out.println(discoverPageObject.getTitleSecondCardSelected());
        Assert.assertEquals(discoverPageObject.getTitleThirdCardSelected(), Constants.THIRD_CARD_TITLE_SELECTED);
        System.out.println(discoverPageObject.getTitleThirdCardSelected());
        log.info("TC03 - Verify gamification toast message");
        verifyEquals(discoverPageObject.getGamificationToastMsg(), Constants.TOAST_MSG_BOARD_CREATED);
        log.info("TC03 - Verify board components");
        verifyTrue(discoverPageObject.isLinkPreviewDisplayed());
        Assert.assertEquals(discoverPageObject.getNumberOfContributors(), "+1 contributor");
        Assert.assertTrue(discoverPageObject.getLastUpdate().contains(discoverPageObject.getCurrentDate()));
    }
}
