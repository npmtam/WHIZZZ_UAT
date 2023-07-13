package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Contains the common functions to re-use in the Page Object classes
 */
public class AbstractPage {
    private WebDriver driver;
    private WebElement element;
    private By by;
    private Actions action;
    private JavascriptExecutor jsExecutor;
    private WebDriverWait waitExplicit;
    private Select select;
    private List<WebElement> elements;
    private long shortTimeout = 3;
    private long longTimeout = 30;
    private Set<String> allWindows;

    /**
     * Constructor to mapping driver variable
     */
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public void clickToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    /**
     * Click to element with the dynamic locators
     */
    public void clickToElement(String locator, String value) {
        locator = String.format(locator, value);
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void clickToElementByJS(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void clickToElementByJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void clickToElementByJS(String locator, String value) {
        locator = String.format(locator, value);
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public boolean isElementDisplayed(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitToElementVisible(locator);
        return element.isDisplayed();
    }

    /**
     * Check for the dynamic locator is displayed
     *
     * @param locator - xpath locator
     * @param value   - value inject to the locator
     * @return element display or not
     */
    public boolean isElementDisplayed(String locator, String value) {
        locator = String.format(locator, value);
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();
    }

    public void sendKeyToElement(String locator, String textValue, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(By.xpath(locator));
        element.clear();
        element.sendKeys(textValue);
    }

    public void waitToElementVisible(String locator) {
        by = By.xpath(locator);
        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitToElementClickable(String locator){
        by = By.xpath(locator);
        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * This function is using for the dynamic locators
     *
     * @param locator: dynamic xpath
     * @param values:  the key inject to the xpath
     */
    public void waitToElementVisible(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        by = By.xpath(locator);
        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void setAttributeValue(String locator, String attribute, String value) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
    }

    public String getTextElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public String getTextElement(String locator, String value) {
        locator = String.format(locator, value);
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    /**
     * Override implicitWait in some case to reduce the wait time
     *
     * @param timeout: seconds to wait
     */
    public void overideGlobalTimeout(long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    /**
     * Check the element is present in the DOM before the actions
     */
    public boolean isElementPresentInDOM(String locator) {
        overideGlobalTimeout(shortTimeout);
        elements = driver.findElements(By.xpath(locator));
        overideGlobalTimeout(longTimeout);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the element is present in the DOM before the actions (use for dynamic xpath)
     */
    public boolean isElementPresentInDOM(String locator, String value) {
        overideGlobalTimeout(shortTimeout);
        locator = String.format(locator, value);
        elements = driver.findElements(By.xpath(locator));
        overideGlobalTimeout(longTimeout);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * For some elements inside the iframe, have to switch to iframe before locating the element
     *
     * @param locator: iframe xpath
     */
    public void switchToFrameOrIframe(String locator) {
        element = driver.findElement(By.xpath(locator));
        driver.switchTo().frame(element);
    }

    /**
     * Get the attribute value of the element
     *
     * @param locator:       xpath of the element
     * @param attributeName: the attribute name
     * @return the attribute value of the element located
     */
    public String getAttributeValue(String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getAttributeValue(String locator, String value, String attributeName) {
        locator = String.format(locator, value);
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }


    public boolean isElementDisabled(String locator) {
        element = driver.findElement(By.xpath(locator));
        return !element.isEnabled();
    }

    public void openNewTab(String url) {
        jsExecutor.executeScript("window.open('" + url + "','_blank');");
    }

    public void switchToWindowsByTitle(String title) {
        allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            try {
                driver.switchTo().window(runWindows);
                String currentWindow = driver.getTitle();
                if (currentWindow.equals(title)) {
                    break;
                }
            } catch (NoSuchWindowException e){
                System.out.println("NoSuchWindowException got handled");
            }
        }
    }

    public void switchToParentIframe() {
        driver.switchTo().parentFrame();
    }

    public int countElements(String locators) {
        overideGlobalTimeout(shortTimeout);
        elements = driver.findElements(By.xpath(locators));
        overideGlobalTimeout(longTimeout);
        return elements.size();
    }

    public void selectItemInDropdown(String locator, String valueItem) {
        select = new Select(driver.findElement(By.xpath(locator)));
        select.selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(String parentLocator, String allItemsLocator, String expectedItem)
            throws InterruptedException {
        WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", parentDropdown);
//        jsExecutor.executeScript("arguments[0].click();", parentDropdown);
        parentDropdown.click();
        Thread.sleep(1000);

        // wait for elements
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));

        // Define list to store all listed items
        List<WebElement> allItems = driver.findElements(By.xpath(allItemsLocator));

        // Through each item to find the item
        // For each
        for (WebElement item : allItems) {
            if (item.getText().equals(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                Thread.sleep(1000);
                item.click();
                Thread.sleep(1000);
                break;
            }
        }
    }

    public void sleepInSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void hoverMouseToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).perform();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public WebElement getLastElement(String locator) {
        elements = driver.findElements(By.xpath(locator));
        element = elements.get(elements.size() - 1);
        return element;
    }

    public String getCurrentDate(){
        LocalDate currentDateTime = LocalDate.now();
        int currentDay = currentDateTime.getDayOfMonth();
        String currentMonth = currentDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
        int currentYear = currentDateTime.getYear();

        String currentDate = currentDay + " " + currentMonth + " " + currentYear;
        return currentDate;
    }

    public List<String> getTextListElements(String locator) {
        String textElement = null;
        List<String> allText = new ArrayList<>();
        elements = driver.findElements(By.xpath(locator));
        for (WebElement item : elements) {
            textElement = item.getText();
            System.out.println(textElement);
            allText.add(textElement);
        }
        return allText;
    }
}
