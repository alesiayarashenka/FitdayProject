package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ControlPanelPage;
import pages.HeaderPage;

public class UserSettingsSteps extends BaseSteps {
    private ControlPanelPage controlPanelPage;
    private HeaderPage headerPage;

    public UserSettingsSteps(WebDriver driver) {
        headerPage = new HeaderPage(driver);
        controlPanelPage = new ControlPanelPage(driver);
    }

    @Step("Open control panel page")
    public UserSettingsSteps openControlPanelPage(String pageName) {
        controlPanelPage.openPage(IConstants.CONTROL_PANEL_PAGE_URL);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Open edit avatar page")
    public UserSettingsSteps openEditAvatarPage(String pageName) {
        controlPanelPage.directToEditAvatar(pageName);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Choose custom avatar to upload image")
    public UserSettingsSteps chooseCustomAvatar() {
        controlPanelPage.isCheckboxAvatarSelected("Do not use an avatar");
        controlPanelPage.selectAvatarCheckbox("Use Custom Avatar");
        controlPanelPage.isCheckboxAvatarSelected("Use Custom Avatar");
        return this;
    }

    @Step("Upload avatar image")
    public UserSettingsSteps uploadUserAvatar(String expectedResult) {
        controlPanelPage.uploadUserAvatar();
        Assert.assertEquals(controlPanelPage.getSaveAvatarMessageTest(), expectedResult);
        return this;
    }
}
