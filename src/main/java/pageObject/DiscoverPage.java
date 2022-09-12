package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUI.DiscoverUI;

public class DiscoverPage extends AbstractPage {
    private WebDriver driver;

    public DiscoverPage(WebDriver driver){
        super(driver);
        this.driver = driver;
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

    public void assignPOC(String pocName){
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
        sleepInSeconds(1);
        waitToElementVisible(DiscoverUI.TOAST_MESSAGE_CONTENT);
        return getTextElement(DiscoverUI.TOAST_MESSAGE_CONTENT);
    }

    public String getTagNameDetailsPage(){
        waitToElementVisible(DiscoverUI.TAG_FIELD_IN_DETAILS_PAGE);
        return getTextElement(DiscoverUI.TAG_FIELD_IN_DETAILS_PAGE);
    }

    public String getLastUpdate(){
        waitToElementVisible(DiscoverUI.LAST_UPDATE_FIELD);
        return getTextElement(DiscoverUI.LAST_UPDATE_FIELD);
    }
}
