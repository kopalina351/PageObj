package pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.substring;

public class WomenPage {
    private final WebDriverWait webDriverWait;
    WebDriver driver;
    Logger logger;
    final String errorElement = "Can't work with element ";
    final String errorDD = "Can not work with dropdown ";
    final String errorList = "Can not work with list of elements ";
    final String errorText = "Can not work with text ";
    final String errorCheckBox = "Can not work with checkbox ";
    final String errorEqual = "Number of filtered elements not equal number of elements on page ";


    @FindBy(className = "cat-name")
    WebElement isPageWomenPage;

    @FindBy(id = "selectProductSort")
    WebElement ddSort;

    @FindBy(xpath = "//div[@class='right-block']//span[@class='price product-price']")
    WebElement listElement;




    public WomenPage(WebDriver exterDriver) {
        this.driver = exterDriver;
        logger = Logger.getLogger(getClass());
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    /**
     * Metod checks Women Page is displayed
     */

    public void isPageWomenDisplayed() {
        try {
            isPageWomenPage.isDisplayed();
            logger.info("Page 'Women' was displayed");
        } catch (Exception e) {
            logger.error(errorText);
            Assert.fail(errorText);
        }
    }

    /**
     *Metod checks that elements are sorted by price (Asc)
     */
    public void checkSortElementsByAscPrice() {
        waitSomeSec(3);
        List<WebElement> listElementsWithoutSort = listElement.findElements(By.xpath("//div[@class='right-block']//span[@class='price product-price']"));
        for (int i = 0; i < listElementsWithoutSort.size()-1; i++) {
            try {
                if ((Float.parseFloat(substring((listElementsWithoutSort.get(i).getText()), 1, 7)))
                        <= (Float.parseFloat(substring((listElementsWithoutSort.get(i + 1).getText()), 1, 7)))) ;
                logger.info("List of elements was getted");
            }catch (Exception e){
                logger.error(errorElement);
                Assert.fail(errorElement);
            }
        }
    }

    /**
     * Metod clicks on color filter and checks number of filtered elements
     * @param color
     * @return
     */

    public String clickOnColorFilterAndCheckNumberOfFilteredElements(String color) {
        waitSomeSec(3);
        int number=0;
        try {
            driver.findElement(By.xpath("//label[@class=\"layered_color\"]/a[contains(text(), " + color + ")]")).click();
            logger.info("Filter '" + color + "' was clicked");
        } catch (Exception e) {
            logger.error(errorCheckBox);
            Assert.fail(errorCheckBox);
        }
        waitSomeSec(3);
        try {
            number = Integer.parseInt(substring(driver.findElement(By.xpath("//label[@class=\"layered_color\"]/a[contains(text(), " + color + ")]/span")).getText(), 2, 1));
            logger.info("Number of filtered elements was getted");
        } catch (Exception e) {
            logger.error(errorElement);
            Assert.fail(errorElement);
        }
        try {
            List<WebElement> elementsOfFiltered = driver.findElements(By.xpath("//ul[@class=\"product_list grid row\"]/li"));
            if (number == elementsOfFiltered.size()) {
                logger.info("Number of filtered elements equal " + elementsOfFiltered.size());
            } else logger.error(errorEqual);
        }catch (Exception e){
            logger.error(errorList);
            Assert.fail(errorList);
        }
        return color;
    }

    /**
     * Metod selects kind of sorting in drop down by Value
     * @param valueSort
     */
    public void selectedValueInDDSort(String valueSort) {
        try {
            Select select = new Select(ddSort);
            select.selectByValue(valueSort);
            logger.info("'" + valueSort + "' was selected in DD");
        } catch (Exception e) {
            logger.error(errorDD);
            Assert.fail(errorDD);
        }
    }

    /**
     *
     * @param sec
     */
    private void waitSomeSec (int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
