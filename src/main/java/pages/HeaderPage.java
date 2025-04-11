package pages;

import elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HeaderPage extends BasePage {
    private static final String WELCOME_MEMBER_XPATH = "//div[contains(@class,'member-form')]/*[contains(text(),'Welcome, ')]/*[contains(text(),'%s')]";
    private static final String PAGE_ITEM_XPATH = "//h1//*[contains(text(),'%s')]";

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking opened page
     *
     * @return
     */
    public boolean isPageOpened(String option) {
        WebElement pageItem = driver.findElement(By.xpath(String.format(PAGE_ITEM_XPATH, option)));
        waiter.waitForPageOpened((By.xpath(String.format(PAGE_ITEM_XPATH, option))), driver);
        return pageItem.isDisplayed();
    }

    /**
     * This is checking visible user's name on the forum page after login
     *
     * @return
     */
    public boolean isForumsOpened(String user) {
        WebElement welcomeMessage = driver.findElement(By.xpath(String.format(WELCOME_MEMBER_XPATH, user)));
        waiter.waitForPageOpened((By.xpath(String.format(WELCOME_MEMBER_XPATH, user))), driver);
        return welcomeMessage.isDisplayed();
    }

    /**
     * This is select option in list of accessible user's pages in header to direct to subscription page
     *
     * @return
     */
    public void selectUsersPage(String option, String pageName) {
        new Dropdown(driver).userSelectOption(option);
        isPageOpened(pageName);
    }

    /**
     * This is select option in list of accessible user's pages in header to logout
     *
     * @return
     */
    public void selectLogout(String option) {
        new Dropdown(driver).userSelectOption(option);
    }


    /**
     * This is select option to logout in pop-ap form
     *
     * @return
     */
    public LoginPage agreementToLogoutInForm(String question) {
        String alertText = driver.switchTo().alert().getText().trim();
        Assert.assertEquals(alertText, question);
        driver.switchTo().alert().accept();
        return new LoginPage(driver);
    }
}