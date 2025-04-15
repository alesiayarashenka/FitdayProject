package pages;

import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

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
    public void selectUsersSubscriptionPage(String option, String pageName) {
        new Dropdown(driver).userSelectSubscrOption(option);
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
     * This checks option names in dropdown
     *
     * @return
     */
    public void checkOptionsNameInList(List<String> optionNameList, List<String> optionNames) {
        for (int i = 0; i < optionNameList.size(); i++)
            for (String name : optionNames) {
                Assert.assertTrue(optionNameList.get(i).contains(name));
            }
    }

    /**
     * This checks tabs in header are displayed
     *
     * @return
     */
    public void isTabsDisplayed(String... tabNames) {
        for (int i = 0; i < tabNames.length; i++) {
            WebElement tab = driver.findElement(By.xpath(String.format(TAB_HEADER_XPATH, tabNames[i])));
            Assert.assertTrue(tab.isDisplayed());
        }
    }

    /**
     * This opens header user dropdown on the forum page
     *
     * @return
     */
    public HeaderPage openHeaderUserDropdown() {
        new Dropdown(driver).userOpenHeaderDropdown();
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