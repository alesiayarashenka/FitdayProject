package pages;

import elements.Button;
import elements.Input;
import entity.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiters.Waiter;

import java.time.Duration;

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
        waiter.waitForPageLoaded();
        new Input(driver, "username").writeTextToInput(user.getUsername());
        new Input(driver, "password").writeTextToInput(user.getPassword());
        new Button(driver).clickButton(driver.findElement(LOGIN_BUTTON));
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

    public LoginPage waitForPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    public String getFieldColor(String element) {
        String titleColor = driver.findElement(By.cssSelector(element)).getCssValue("border-color");
        Color color = Color.fromString(titleColor);
        return color.asHex();
    }
}
