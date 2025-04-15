package pages;

import elements.Button;
import elements.Checkbox;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ControlPanelPage extends BasePage {
    private static final String SETTINGS_PAGE_XPATH = "//a[contains(text(),'%s')]";
    private static final String CHECKBOX_AVATAR_XPATH = "//*[contains(text(),'%s')]/input[@type='radio']";
    public static final String PATH_TO_THE_FILE = System.getProperty("user.dir") + "/src/test/resources/1.jpg";
    public static final By SELECT_FILE = By.xpath("//input[@type='file']");
    public static final By SAVE_CHANGES_BUTTON = By.xpath("//input[@value='Save Changes']");
    public static final By SAVE_AVATAR_MESSAGE = By.xpath("//div[@class='raisedbox']");

    public ControlPanelPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This directs to edit avatar
     *
     * @return
     */
    public ControlPanelPage directToEditAvatar(String settingsName) {
        new Button(driver).clickButton(driver.findElement(By.xpath(String.format(SETTINGS_PAGE_XPATH, settingsName))));
        return this;
    }

    /**
     * This clicks at avatar checkbox
     *
     * @return
     */
    public void selectAvatarCheckbox(String checkboxName) {
        new Checkbox(driver).setCheckboxValue((By.xpath(String.format(CHECKBOX_AVATAR_XPATH, checkboxName))),true);
    }

    /**
     * This checks selected avatar checkbox
     *
     * @return
     */
    public boolean checkboxAvatarIsSelected(String checkboxName) {
        return new Checkbox(driver).isCheckBoxChecked(driver.findElement(By.xpath(String.format(CHECKBOX_AVATAR_XPATH, checkboxName))));
    }

    /**
     * This upload avatar image
     *
     * @return
     */
    public void uploadUserAvatar() {
        driver.findElement(SELECT_FILE).sendKeys(PATH_TO_THE_FILE);
        waiter.waitForButtonClickable((SAVE_CHANGES_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(SAVE_CHANGES_BUTTON));
        log.info("User uploaded avatar");
        waiter.waitForPageLoaded();
    }

    /**
     * This checks uploaded avatar image
     *
     * @return
     */
    public String getSaveAvatarMessageTest() {
        try {
            log.info("Getting error message text.");
            return driver.findElement(SAVE_AVATAR_MESSAGE).getText();
        } catch (Exception e) {
            log.error("Failed to get error message text.", e);
            return "";
        }
    }
}
