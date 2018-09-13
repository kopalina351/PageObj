package prestaShop;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.WomenPage;

public class CheckNumbereOfFilteredElements {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);
    WomenPage womenPage = new WomenPage(driver);

    @Test
    public void CheckSortingByAscPrice() {

        homePage.openBrowserAndHomePage();
        homePage.clickOnTabWomen();
        womenPage.isPageWomenDisplayed();
        womenPage.selectedValueInDDSort("price:asc");
        womenPage.clickOnColorFilterAndCheckNumberOfFilteredElements("Pink");
    }

    @After
    public void tearDown() { homePage.closeBrowser();
    }
}