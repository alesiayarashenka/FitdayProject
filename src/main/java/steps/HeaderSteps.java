package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.HeaderPage;
import pages.LoginPage;

public class HeaderSteps extends BaseSteps {
    private HeaderPage headerPage;
    private LoginPage loginPage;

    public HeaderSteps(WebDriver driver) {
        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Step("Check welcome message in header on forum page after login")
    public HeaderSteps checkWelcomeMessageInHeader(String user) {
        headerPage.isForumsOpened(user);
        return this;
    }

    @Step("Select logout option, agreement to logout, logout")
    public HeaderSteps directToSubscriptionPage(String text) {
        headerPage.selectLogout("Log Out");
        headerPage.agreementToLogoutInForm(text);
        loginPage.waitForLoginPageOpened();
        return this;
    }
}
