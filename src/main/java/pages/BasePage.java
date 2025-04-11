package pages;

import constants.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiter;

public class BasePage implements IConstants {
    WebDriver driver;
    Waiter waiter = new Waiter();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * This is opening page by url
     *
     * @param url
     */
    public void openPage(String url) {
        waiter.waitForPageLoaded();
        driver.get(url);
    }
}
