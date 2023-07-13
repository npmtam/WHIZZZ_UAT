package pageObject;

import commons.AbstractPage;
import commons.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUI.DiscoverUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DiscoverPageObject extends AbstractPage {
    private WebDriver driver;
    private String first,second,third;
    private String configFileName;
    private Properties properties;
    private FileInputStream configFile;
    private String rootFolder = System.getProperty("user.dir");

    public DiscoverPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
        configFileName = "config.properties";
        properties = new Properties();
        try {
            configFile = new FileInputStream(rootFolder + File.separator + configFileName);
            properties.load(configFile);
            configFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        first = "1";
        second = "2";
        third = "3";
    }

    public String getAccount(String keyword){
        if(keyword.equalsIgnoreCase("email")){
            return properties.getProperty("email");
        } else if (keyword.equalsIgnoreCase("password")){
            return properties.getProperty("password");
        }
        return null;
    }

    public void clickToSurveyIfAvailable(){
        if(isElementPresentInDOM(DiscoverUI.SURVEY_POPUP)){
            waitToElementVisible(DiscoverUI.SURVEY_10_POINTS);
            clickToElement(DiscoverUI.SURVEY_10_POINTS);
            clickToElement(DiscoverUI.SURVEY_SUBMIT_BUTTON);
        }
    }

    public void clickToCreateCard(){
        waitToElementVisible(DiscoverUI.CREATE_NEW_BUTTON);
        clickToElement(DiscoverUI.CREATE_NEW_BUTTON);

        waitToElementVisible(DiscoverUI.CREATE_NEW_CARD_BUTTON);
        clickToElement(DiscoverUI.CREATE_NEW_CARD_BUTTON);
    }

    public void createANewCard(String title, String description){
        waitToElementVisible(DiscoverUI.CARD_TITLE_TEXTBOX);
        sendKeyToElement(DiscoverUI.CARD_TITLE_TEXTBOX, title);

        waitToElementVisible(DiscoverUI.CARD_DESCRIPTION_TEXTAREA);
        sendKeyToElement(DiscoverUI.CARD_DESCRIPTION_TEXTAREA, description);
    }

    public void clickToSubmitBtn(){
        waitToElementVisible(DiscoverUI.SUBMIT_BUTTON);
        clickToElement(DiscoverUI.SUBMIT_BUTTON);
    }

    public void selectReviewInterval(String option){
        waitToElementVisible(DiscoverUI.REVIEW_INTERVAL_DROPDOWN);
        try {
            selectItemInDropdown(DiscoverUI.REVIEW_INTERVAL_DROPDOWN, DiscoverUI.REVIEW_INTERVAL_OPTIONS, option);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assignPOC(String pocName){
        waitToElementVisible(DiscoverUI.EXPAND_POC_SECTION);
        clickToElement(DiscoverUI.EXPAND_POC_SECTION);
        waitToElementVisible(DiscoverUI.POC_BUTTON);
        clickToElement(DiscoverUI.POC_BUTTON);
        waitToElementVisible(DiscoverUI.POC_SEARCH_BAR);
        sendKeyToElement(DiscoverUI.POC_SEARCH_BAR, pocName);
        sleepInSeconds(1);
        waitToElementVisible(DiscoverUI.POC_FIRST_RESULT);
        clickToElement(DiscoverUI.POC_FIRST_RESULT);
        clickToElement(DiscoverUI.APPLY_BUTTON);
    }

    public void addTag(String tagName){
        waitToElementVisible(DiscoverUI.EXPAND_TAG_SECTION);
        clickToElement(DiscoverUI.EXPAND_TAG_SECTION);
        waitToElementVisible(DiscoverUI.ADD_TAG_FIELD);
        scrollToElement(DiscoverUI.ADD_TAG_FIELD);
        sendKeyToElement(DiscoverUI.ADD_TAG_FIELD, tagName);
        driver.findElement(By.xpath(DiscoverUI.ADD_TAG_FIELD)).sendKeys(Keys.ENTER);
    }

    public void clickToOptionMenu(){
        waitToElementVisible(DiscoverUI.OPTIONS_MENU_BUTTON);
        clickToElement(DiscoverUI.OPTIONS_MENU_BUTTON);
    }

    public void assignContributor(String contributorName){
        clickToOptionMenu();
        waitToElementVisible(DiscoverUI.CONTRIBUTOR_MENU_BUTTON);
        clickToElement(DiscoverUI.CONTRIBUTOR_MENU_BUTTON);
        waitToElementVisible(DiscoverUI.CONTRIBUTOR_SEARCH_BAR);
        sendKeyToElement(DiscoverUI.CONTRIBUTOR_SEARCH_BAR, contributorName);
        sleepInSeconds(1);
        waitToElementVisible(DiscoverUI.POC_FIRST_RESULT);
        clickToElement(DiscoverUI.POC_FIRST_RESULT);
        waitToElementVisible(DiscoverUI.APPLY_BUTTON);
        clickToElement(DiscoverUI.APPLY_BUTTON);
    }

    public void previewTheCard(){
        clickToOptionMenu();
        waitToElementVisible(DiscoverUI.PREVIEW_MENU_BUTTON);
        clickToElement(DiscoverUI.PREVIEW_MENU_BUTTON);
    }

    public String getNumberOfContributors(){
        waitToElementVisible(DiscoverUI.NUMBER_OF_CONTRIBUTOR);
        return getTextElement(DiscoverUI.NUMBER_OF_CONTRIBUTOR);
    }

    public String getNameOfPOC(){
        waitToElementVisible(DiscoverUI.NAME_OF_POC);
        return getTextElement(DiscoverUI.NAME_OF_POC);
    }

    public void clickToPublishButton(){
        waitToElementVisible(DiscoverUI.PUBLISH_BUTTON);
        clickToElement(DiscoverUI.PUBLISH_BUTTON);
    }

    public String getToastMessage(){
        sleepInSeconds(2);
        waitToElementVisible(DiscoverUI.TOAST_MESSAGE_CONTENT);
        return getTextElement(DiscoverUI.TOAST_MESSAGE_CONTENT);
    }

    public String getGamificationToastMsg(){
        waitToElementVisible(DiscoverUI.GAMIFICATION_TOAST_MESSAGE);
        return getTextElement(DiscoverUI.GAMIFICATION_TOAST_MESSAGE);
    }
    public String getTagNameDetailsPage(){
        waitToElementVisible(DiscoverUI.TAG_FIELD_IN_DETAILS_PAGE);
        return getTextElement(DiscoverUI.TAG_FIELD_IN_DETAILS_PAGE);
    }

    public String getTagNamePublishedPage(){
        waitToElementVisible(DiscoverUI.TAG_NAME_IN_PUBLISHED_PAGE);
        return getTextElement(DiscoverUI.TAG_NAME_IN_PUBLISHED_PAGE);
    }
    public String getLastUpdate(){
        waitToElementVisible(DiscoverUI.LAST_UPDATE_FIELD);
        return getTextElement(DiscoverUI.LAST_UPDATE_FIELD);
    }

    public void inputLinkPreviewToDescription(String link){
        waitToElementVisible(DiscoverUI.CARD_DESCRIPTION_TEXTAREA);
        driver.findElement(By.xpath(DiscoverUI.CARD_DESCRIPTION_TEXTAREA)).sendKeys(Keys.ENTER);
        sendKeyToElement(DiscoverUI.CARD_DESCRIPTION_TEXTAREA, link);
        driver.findElement(By.xpath(DiscoverUI.CARD_DESCRIPTION_TEXTAREA)).sendKeys(Keys.ENTER);
    }

    public boolean isLinkPreviewDisplayed(){
        waitToElementVisible(DiscoverUI.LINK_PREVIEW_THUMBNAIL_IMG);
        return isElementDisplayed(DiscoverUI.LINK_PREVIEW_THUMBNAIL_IMG);
    }

    public void backToHomePage(){
        waitToElementVisible(DiscoverUI.EMMA_LOGO);
        clickToElement(DiscoverUI.EMMA_LOGO);
    }

    public void clickToCreateBoard(){
        waitToElementVisible(DiscoverUI.CREATE_NEW_BUTTON);
        clickToElement(DiscoverUI.CREATE_NEW_BUTTON);

        waitToElementVisible(DiscoverUI.CREATE_NEW_BOARD_BUTTON);
        clickToElement(DiscoverUI.CREATE_NEW_BOARD_BUTTON);
    }

    public void selectFirstThreeCards(){
        waitToElementVisible(DiscoverUI.SELECT_CARD_BUTTON, first);
        clickToElement(DiscoverUI.SELECT_CARD_BUTTON, first);
        Constants.FIRST_CARD_TITLE_SELECTED = getTextElement(DiscoverUI.SELECTED_CARD_TITLE, first);
        clickToElement(DiscoverUI.SELECT_CARD_BUTTON, second);
        Constants.SENCOND_CARD_TITLE_SELECTED = getTextElement(DiscoverUI.SELECTED_CARD_TITLE,second);
        clickToElement(DiscoverUI.SELECT_CARD_BUTTON, third);
        Constants.THIRD_CARD_TITLE_SELECTED = getTextElement(DiscoverUI.SELECTED_CARD_TITLE, third);
    }

    public void clickToAddCardsButton(){
        waitToElementVisible(DiscoverUI.ADD_CARD_TO_BOARD_BUTTON);
        clickToElement(DiscoverUI.ADD_CARD_TO_BOARD_BUTTON);
    }

    public void clickToAddCardSelected(){
        sleepInSeconds(1);
        waitToElementClickable(DiscoverUI.ADD_CARD_SELECTED_BUTTON);
        clickToElement(DiscoverUI.ADD_CARD_SELECTED_BUTTON);
    }

    public String getTitleFirstCardSelected(){
        waitToElementVisible(DiscoverUI.SELECTED_CARD_TITLE, first);
        return getTextElement(DiscoverUI.SELECTED_CARD_TITLE, first);
    }

    public String getTitleSecondCardSelected(){
        waitToElementVisible(DiscoverUI.SELECTED_CARD_TITLE,second);
        return getTextElement(DiscoverUI.SELECTED_CARD_TITLE, second);
    }

    public String getTitleThirdCardSelected(){
        waitToElementVisible(DiscoverUI.SELECTED_CARD_TITLE, third);
        return getTextElement(DiscoverUI.SELECTED_CARD_TITLE, third);
    }
}
