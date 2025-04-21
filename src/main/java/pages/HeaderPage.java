package pages;

import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class HeaderPage extends BasePage {
    private static final String WELCOME_MEMBER_XPATH = "//div[contains(@class,'member-form')]/*[contains(text(),'Welcome, ')]/*[contains(text(),'%s')]";
    private static final String PAGE_ITEM_XPATH = "//h1//*[contains(text(),'%s')]";
    private static final String TAB_HEADER_XPATH = "//div[@class='top-nav-left']/child::ul/child::li/a[contains(text(),'%s')]";
    private static final String TAB_DROPDOWN_LIST_XPATH = "//*[@href][contains(text(),'%s')]/ancestor::ul[@class='dropdown']";
    private static final By HEADER_USER_DROPDOWN_XPATH = By.xpath("//ul[contains(@class,'f-dropdown open')]");

    Actions action = new Actions(driver);

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking opened page
     *
     * @return
     */
    public boolean isPageOpened(String option) {
        waiter.waitForPageOpened((By.xpath(String.format(PAGE_ITEM_XPATH, option))), driver);
        WebElement pageItem = driver.findElement(By.xpath(String.format(PAGE_ITEM_XPATH, option)));
        log.info("Page {} is opened", option);
        return pageItem.isDisplayed();
    }

    /**
     * This is checking visible user's name on the forum page after login
     *
     * @return
     */
    public boolean isForumsOpened(String user) {
        log.info("Wait for opening main forum page");
        waiter.waitForPageOpened((By.xpath(String.format(WELCOME_MEMBER_XPATH, user))), driver);
        log.info("Welcome message is visibility");
        WebElement welcomeMessage = driver.findElement(By.xpath(String.format(WELCOME_MEMBER_XPATH, user)));
        log.info("Forum page is opened and welcome message with name {} is displayed", System.getenv("username"));
//      log.info("Forum page is opened and welcome message with name {} is displayed", user);  //---for local
        return welcomeMessage.isDisplayed();
    }

    /**
     * This is select option in list of accessible user's pages in header to direct to subscription page
     *
     * @return
     */
    public void selectUsersSubscriptionPage(String option, String pageName) {
        new Dropdown(driver).userSelectSubscrOption(option);
        log.info("Page {} is opened", pageName);
        isPageOpened(pageName);
    }

    /**
     * This is select option in list of accessible user's pages in header to logout
     *
     * @return
     */
    public void selectLogout(String option) {
        new Dropdown(driver).userSelectSubscrOption(option);
    }


    /**
     * This selects option to logout in pop-ap form
     *
     * @return
     */
    public LoginPage agreementToLogoutInForm() {
        driver.switchTo().alert().accept();
        log.info("User has logged out");
        return new LoginPage(driver);
    }

    /**
     * This gets text in logout pop-ap form
     *
     * @return
     */
    public String getTextInLogoutForm() {
        return driver.switchTo().alert().getText().trim();
    }

    /**
     * This is move to tab header option
     *
     * @return
     */
    public HeaderPage moveToTabHeaderOption(String tabName) {
        WebElement tab = driver.findElement(By.xpath(String.format(TAB_HEADER_XPATH, tabName)));
        action.moveToElement(tab).build().perform();
        log.info("Tab {} is displayed", tabName);
        return this;
    }

    /**
     * This selects tabs titles in list
     *
     * @return
     */
    public List<String> getTitlesInTab(String tabName) {
        ArrayList<String> forumList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(By.xpath(String.format(TAB_DROPDOWN_LIST_XPATH, tabName)));
        for (WebElement webElement : optionsName) {
            forumList.add(webElement.getText());
        }
        return forumList;
    }

    /**
     * This get list of tabs in header
     *
     * @return
     */
    public List<WebElement> getTabHeaderList(String... tabName) {
        ArrayList<WebElement> tabList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(By.xpath(String.format(TAB_HEADER_XPATH, tabName)));
        for (WebElement webElement : optionsName) {
            tabList.add(webElement);
        }
        return tabList;
    }

    /**
     * This opens header user dropdown on the forum page
     *
     * @return
     */
    public HeaderPage openHeaderUserDropdown() {
        new Dropdown(driver).userOpenHeaderDropdown();
        log.info("Header dropdown is opened");
        return this;
    }

    /**
     * This checks option names in header dropdown
     *
     * @return
     */
    public List<String> getTitlesInHeaderDropdown() {
        ArrayList<String> optionList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(HEADER_USER_DROPDOWN_XPATH);
        for (WebElement webElement : optionsName) {
            optionList.add(webElement.getText());
        }
        return optionList;
    }
}