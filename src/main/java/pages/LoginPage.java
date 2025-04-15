package pages;

import elements.Button;
import elements.Input;
import entity.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

@Log4j2
public class LoginPage extends BasePage {

    public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By FORGOT_PASSWORD_BUTTON = By.xpath("//*[@href='/auth/requestPassword']");
    public static final By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@id='SubmitCreate']");
    public static final By ERROR_MESSAGE = By.xpath("//div[@role='alert']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This logins by object
     *
     * @return
     */
    public ForumsPage login(User user) {
        new Input(driver, "username").writeTextToInput(user.getUsername());
        new Input(driver, "password").writeTextToInput(user.getPassword());
        waiter.waitForButtonClickable((LOGIN_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(LOGIN_BUTTON));
        log.info("User is registered with username: {}", user.getUsername());
        return new ForumsPage(driver);
    }

    /**
     * This is checking text of error messages by login
     *
     * @return
     */
    public String getErrorMessageTest() {
        try {
            log.info("Getting error message text.");
            return driver.findElement(ERROR_MESSAGE).getText();
        } catch (Exception e) {
            log.error("Failed to get error message text.", e);
            return "";
        }
    }

    /**
     * This is waiting for open login page
     *
     * @return
     */
    public LoginPage waitForLoginPageOpened() {
        waiter.waitForPageOpened((LOGIN_BUTTON), driver);
        return this;
    }

    /**
     * This is checking login button is displayed
     *
     * @return
     */
    public boolean isLoginButtonDisplayed() {
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    /**
     * This is checking field color
     *
     * @return
     */
    public String getFieldColor(String element) {
        String titleColor = driver.findElement(By.cssSelector("#" + element)).getCssValue("border-color");
        return Color.fromString(titleColor).asHex();
    }
}
