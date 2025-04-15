package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.LoginPage;

import java.util.List;

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
    public HeaderSteps logoutFromApp(String text) {
        headerPage.selectLogout("Log Out");
        Assert.assertEquals(headerPage.getTextInLogoutForm(), text);
        headerPage.agreementToLogoutInForm();
        loginPage.waitForLoginPageOpened();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        return this;
    }

    @Step("Check existing header tabs")
    public HeaderSteps checkExistingHeaderTabs(String... tab) {
        headerPage.isTabsDisplayed(tab);
        return this;
    }

    @Step("Check dropdown options in header user dropdown")
    public HeaderSteps checkDropdownOptionsInHeaderUserDropdown(List<String> forumNames) {
        headerPage.openHeaderUserDropdown()
                .checkOptionsNameInList(headerPage.getTitlesInHeaderDropdown(), forumNames);
        return this;
    }
}
