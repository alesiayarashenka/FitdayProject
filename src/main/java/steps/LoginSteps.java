package steps;

import constants.IConstants;
import entity.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {
    LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    @Step("Login and wait for page loaded")
    public LoginSteps loginAndWaitForPageOpened(User user) {
        loginPage.openPage(IConstants.LOGIN_PAGE_URL);
        loginPage
                .waitForLoginPageOpened()
                .login(user);
        return this;
    }

    @Step("Check validation message user field text")
    public LoginSteps checkValidationMessageUserField(String expectedResult) {
        Assert.assertEquals(loginPage.getErrorMessageTest(), expectedResult);
        return this;
    }

    @Step("Check password and username field color")
    public String checkPasswordAndUsernameFieldColorAtLogin(String element) {
        String color = loginPage.getFieldColor(element);
        return color;
    }
}
