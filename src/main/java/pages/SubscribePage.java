package pages;

import elements.Button;
import elements.Checkbox;
import elements.Dropdown;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class SubscribePage extends HeaderPage {
    private static final By SUBSCRIPTION_ITEM_XPATH = By.xpath("//div[contains(@class,'tcell alt1 smallfont')]");
    public static final By ADD_SUBSCRIPTION_BUTTON = By.xpath("//input[@value='Add Subscription']");
    private static final String SUBSCRIPTION_MESSAGE_IN_FORM = "//p/child::strong";
    public static final By CHECKBOX_SUBSCRIPTION = By.xpath("//input[@value='Check All']");
    public static final By GO_BUTTON = By.xpath("//select[@name='what']/following::input[@value='Go']");

    public SubscribePage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking not existing user's subscriptions
     *
     * @return
     */
    public String getNotExistedUserSubscriptionText() {
        try {
            log.info("Getting not existed subscription message text.");
            return driver.findElement(SUBSCRIPTION_ITEM_XPATH).getText();
        } catch (Exception e) {
            log.error("Failed to get not existed subscription message text.", e);
            return "";
        }
    }

    /**
     * This adds subscription on subscription page
     *
     * @return
     */
    public ThreadPage subscribeToThreadOnSubscribePage() {
        waiter.waitForButtonClickable((ADD_SUBSCRIPTION_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(ADD_SUBSCRIPTION_BUTTON));
        return new ThreadPage(driver);
    }

    /**
     * This checks message after subscription
     *
     * @return
     */
    public String getMessageSubscriptionText() {
        try {
            log.info("Getting successful subscription message text.");
            return driver.findElement(By.xpath(String.format(SUBSCRIPTION_MESSAGE_IN_FORM))).getText();
        } catch (Exception e) {
            log.error("Failed to get successful subscription message text.", e);
            return "";
        }
    }

    /**
     * This opens dropdown subscription to the thread on subscription page
     *
     * @return
     */
    public void checkAddedSubscriptionOption(String dropdownName) {
        new Dropdown(driver).clickAtDropdownField(dropdownName);
    }

    /**
     * This checks existing subscription to the thread on subscription page
     *
     * @return
     */
    public String getSubscriptionFolderText(String dropdownOption) {
        return new Dropdown(driver).getTextInDropdownOption(dropdownOption);
    }

    /**
     * This chooses all subscription threads on subscription page to delete
     *
     * @return
     */
    public void selectCheckboxForDeleteSubscription() {
        new Checkbox(driver).setCheckboxValue(CHECKBOX_SUBSCRIPTION, true);
    }

    /**
     * This checks selected subscription threads on subscription page
     *
     * @return
     */
    public boolean checkboxForDeleteSubscriptionIsSelected() {
        return new Checkbox(driver).isCheckBoxChecked(driver.findElement(CHECKBOX_SUBSCRIPTION));
    }

    /**
     * This clicks to delete selected subscription threads
     *
     * @return
     */
    public SubscribePage deleteChooseFolderFromSubscription(String dropdownName, String option) {
        new Dropdown(driver).selectOptionInDropdown(dropdownName, option);
        waiter.waitForButtonClickable((GO_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(GO_BUTTON));
        return this;
    }
}
