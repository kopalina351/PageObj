package pages;

import libs.ConfigData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class HomePage {
    private final WebDriverWait webDriverWait;
    WebDriver driver;
    Logger logger;
    final String errorMenu = "Can't work with element menu ";
    final String errorButton = "Can not work with button ";
    final String errorCheckBox = "Can not work with checkbox ";
    final String baseUrl = "http://automationpractice.com/index.php";

    @FindBy(xpath = "//a[@title='Women']")
    WebElement tabWomen;



    @FindBy(xpath = ".//a[@class='product-name' and @title='Blouse']")
    WebElement productBlouse;

    @FindBy(xpath = ".//a[@class='logout']")
    WebElement buttonLogOut;


    public HomePage(WebDriver exterDriver) {
        this.driver = exterDriver;
        logger = Logger.getLogger(getClass());
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method opens Browser And HomePage
     */
    public void openBrowserAndHomePage() {
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.get(ConfigData.getCfgValue("BASE_URL"));
            if (driver.getCurrentUrl().toString().equals(baseUrl) == true) {
                logger.info("Home page was opened");
            } else logger.error("Home page wasn't opened");
        } catch (Exception e) {
            logger.error("Can not work with HomePage");
            Assert.fail("Can not work with HomePage");
        }
    }


    /**
     * Method clicks on tab 'Women'
     */
   public void clickOnTabWomen() {
        try {
            tabWomen.click();
            logger.info("Tab 'Women' was clicked");
        } catch (Exception e) {
            logger.error(errorMenu);
            Assert.fail(errorMenu);
        }
    }

    /**
     * Method clicks on product 'Blouse'
     */
    public void clickOnProductBlouse() {
        try {
            productBlouse.click();
            logger.info("Product 'Blouse' was clicked");
        } catch (Exception e) {
            logger.error(errorButton);
            Assert.fail(errorButton);
        }
    }

    /**
     * Method clicks On Button LogOut
     */
    public void clickOnButtonLogOut() {
        try {
            buttonLogOut.click();
            logger.info("Button 'LogOut' was clicked");
        } catch (Exception e) {
            logger.error(errorButton);
            Assert.fail(errorButton);
        }
    }

    /**
     * Method does logOut And Close Browser
     */
    public void logOutAndCloseBrowser() {
        clickOnButtonLogOut();
        driver.quit();
        logger.info("Browser was closed");
    }
    /**
     * Method does Close Browser
     */
    public void closeBrowser() {
        driver.quit();
        logger.info("Browser was closed");
    }
}
