package tests;

import org.testng.annotations.Test;

import java.util.List;

public class ForumTest extends Preconditions implements ITestConstants {

    @Test(description = "Login user, check options in header tabs")
    public void checkOptionsInHeaderTabsTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
//        headerSteps.checkWelcomeMessageInHeader(System.getenv("username"))
        headerSteps.checkWelcomeMessageInHeader()
                .checkExistingHeaderTabs(HEADER_TABS_LIST);
        threadSteps.checkOptionsInTabs("Forums", List.of(FORUM_VALUE_LIST));
        threadSteps.checkOptionsInTabs("Tools", List.of(TOOLS_VALUE_LIST));
    }

    @Test(description = "Login user, check options in header dropdown")
    public void checkOptionsInHeaderDropdownTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
//        headerSteps.checkWelcomeMessageInHeader(System.getenv("username"))
        headerSteps.checkWelcomeMessageInHeader()
                .checkDropdownOptionsInHeaderUserDropdown(List.of(USER_HEADER_OPTIONS_DROPDOWN));
    }

    @Test(description = "Login user, check options in forum tabs")
    public void checkOptionsInForumTabsTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
//        headerSteps.checkWelcomeMessageInHeader(System.getenv("username"));
        headerSteps.checkWelcomeMessageInHeader();
        forumSteps.checkExistingForumTabs(FORUM_TABS_LIST);
    }
}
