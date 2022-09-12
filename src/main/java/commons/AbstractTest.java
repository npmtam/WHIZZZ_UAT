package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    protected final Log log;
    private WebDriver driver;
    private String rootFolder = System.getProperty("user.dir");

    /**
     * Constructor to initialization LogManager
     */
    public AbstractTest() {
        log = LogFactory.getLog(getClass());
    }

    public WebDriver getBrowserDriver(String browserName) {
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "\\FirefoxLogs.txt");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver();
                break;
            case "firefox_headless":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                driver = new FirefoxDriver(options);
                driver.manage().window().setSize(new Dimension(1600, 900));
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--remote-debugging-port=9222");
				chromeOptions.addArguments("--disable-default-apps");
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(chromeOptions);
                break;
            case "chrome_headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options2 = new ChromeOptions();
                options2.setHeadless(true);
                driver = new ChromeDriver(options2);
                break;
            default:
                System.out.println("Please input your browser name!");
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Close and quit browser driver
     * @param driver
     */
    public void closeBrowserAndDriver(WebDriver driver) {
        try {
            // get OS name and convert to lower case
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS Name = " + osName);

            // Declare command line to execute
            String cmd = "";
            if(driver != null) {
                driver.quit();
            }

            if (driver.toString().toLowerCase().contains("chrome")) {
                if(osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            }else if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if(osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            }else if (driver.toString().toLowerCase().contains("firefox")) {
                if(osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("----------------QUIT BROWSER SUCCESS-----------------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * Verify the condition is true
     * Try/catch the exception to keep the test cases still running even when some test cases are failed
     */
    public boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.warn(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;
            //add exception to log file
            log.error(e.getMessage());
        }
        return pass;
    }

    public boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    /**
     * Verify the condition is Failed
     * Try/catch the exception to keep the test cases still running even when some test cases are failed
     */
    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.warn(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            //add exception to log file
            log.error(e.getMessage());
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    /**
     * Verify the actual result and expected result are equals
     * Try/catch the exception to keep the test cases still running even when some test cases are failed
     */
    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        boolean status;
        try {
            if (actual instanceof String && expected instanceof String) {
                actual = actual.toString().trim();
                expected = expected.toString().trim();
                status = (actual.equals(expected));
            } else {
                status = (actual == expected);
            }

            if (status) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.warn(" -------------------------- FAILED -------------------------- ");
                log.warn("Expected: " + expected);
                log.warn("Actual: " + actual);
            }
            Assert.assertEquals(actual, expected, "Value is not matching!");
        } catch (Throwable e) {
            pass = false;
            //add the exception to log
            log.error(e.getMessage());
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

}