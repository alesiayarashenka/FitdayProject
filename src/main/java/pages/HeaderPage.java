package pages;

import elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.Waiter;

public class HeaderPage extends BasePage{
    private static final String WELCOME_MEMBER_XPATH = "//div[contains(@class,'member-form')]/*[contains(text(),'Welcome, ')]/*[contains(text(),'%s')]";
    private static final String PAGE_ITEM_XPATH = "//h1//*[contains(text(),'%s')]";
    Waiter waiter = new Waiter();

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking opened page
     *
     * @return
     */
    public boolean pageIsOpened(String option) {
        WebElement pageItem = driver.findElement(By.xpath(String.format(PAGE_ITEM_XPATH, option)));
        waiter.waitForPageOpened(pageItem, driver);
        return pageItem.isDisplayed();
    }

    /**
     * This is checking visible user's name of the forum page
     *
     * @return
     */
    public boolean forumsIsOpened(String user) {
        WebElement welcomeMessage = driver.findElement(By.xpath(String.format(WELCOME_MEMBER_XPATH, user)));
        waiter.waitForPageOpened(welcomeMessage, driver);
        return welcomeMessage.isDisplayed();
    }

    /**
     * This is select option in list of accessible user's pages
     *
     * @return
     */
    public void selectUsersPage(String option, String pageName) {
        new Dropdown(driver).userSelectOption(option);
        pageIsOpened(pageName);
    }
}