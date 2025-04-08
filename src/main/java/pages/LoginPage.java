package pages;

import elements.Input;
import entity.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import waiters.Waiter;


@Log4j2
public class LoginPage extends BasePage {

    public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By FORGOT_PASSWORD_BUTTON = By.xpath("//*[@href='/auth/requestPassword']");
    public static final By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@id='SubmitCreate']");
    public static final By ERROR_MESSAGE = By.xpath("//div[@role='alert']");
    Waiter waiter = new Waiter();

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
        waiter.checkEnabledButtonAndClick(driver.findElement(LOGIN_BUTTON), driver);
        log.info("User is registered with username: {}", user.getUsername());
        return new ForumsPage(driver);
    }

    /**
     * This is checking text of error messages
     *
     * @return
     */
    public String getErrorMessageTest() {
        String message = driver.findElement(ERROR_MESSAGE).getText();
        log.info("User was not logged: {}", message);
        return message;
    }

    /**
     * This is waiting for open login page
     *
     * @return
     */
    public LoginPage waitForLoginPageOpened() {
        waiter.waitForPageOpened(driver.findElement(LOGIN_BUTTON), driver);
        return this;
    }

    /**
     * This is checking field color
     *
     * @return
     */
    public String getFieldColor(String element) {
        String titleColor = driver.findElement(By.cssSelector("#" + element)).getCssValue("border-color");
        Color color = Color.fromString(titleColor);
        return color.asHex();
    }
}
