package tests;

import org.testng.annotations.Test;

public class UserSettingsTest extends Preconditions implements ITestConstants {

    @Test(description = "Login user, direct to control panel, upload avatar")
    public void uploadAvatarTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        headerSteps.checkWelcomeMessageInHeader(USER);
        userSettingsSteps.openControlPanelPage(CONTROL_PANEL_PAGE)
                .openEditAvatarPage(EDIT_AVATAR_PAGE)
                .chooseCustomAvatar()
                .uploadUserAvatar(SAVE_AVATAR_MESSAGE);
    }

    @Test(description = "Login user, direct to user profile page, fill information 'Biography', clean information")
    public void fillUserInformationTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        headerSteps.checkWelcomeMessageInHeader(USER);
        userInformationSteps.openUserInformationPage(USER_PROFILE_PAGE_NAME)
                .fillUserInformation("I'm from Minsk");
        userInformationSteps.clearUserInformation();
    }
}
