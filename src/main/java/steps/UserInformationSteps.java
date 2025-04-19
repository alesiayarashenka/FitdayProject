package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.HeaderPage;
import pages.UserInformationPage;

public class UserInformationSteps extends BaseSteps {

    private HeaderPage headerPage;
    private UserInformationPage userInformationPage;

    public UserInformationSteps(WebDriver driver) {
        headerPage = new HeaderPage(driver);
        userInformationPage = new UserInformationPage(driver);
    }

    @Step("Open user information page")
    public UserInformationSteps openUserInformationPage(String pageName) throws InterruptedException {
        userInformationPage.openPage(IConstants.PROFILE_PAGE_URL);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Set value in user information field 'Biography'")
    public void fillUserInformation(String value) {
        userInformationPage.chooseUserInformationValue("Biography");
        userInformationPage.isProfileInformationFieldOpened();
        userInformationPage.fillBiographyInformationField(value);
        userInformationPage.saveChangesInUserInformationField("Biography");
        userInformationPage.checkProfileFieldText("profilefield_value_1", value);
    }

    @Step("Clear value in user information field 'Biography'")
    public void clearUserInformation() {
        userInformationPage.chooseUserInformationValue("Biography");
        userInformationPage.isProfileInformationFieldOpened();
        userInformationPage.clearInformationField("ctb_field1");
        userInformationPage.saveChangesInUserInformationField("Biography");
        userInformationPage.checkProfileFieldText("profilefield_value_1", "N/A");
    }
}
