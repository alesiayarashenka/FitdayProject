package tests;

import org.testng.annotations.Test;

public class LogoutTest extends Preconditions implements ITestConstants {

    @Test(description = "Login user and logout")
    public void logoutTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        headerSteps.checkWelcomeMessageInHeader(System.getenv("username"))
                .logoutFromApp(LOGOUT_QUESTION_IN_FORM);
    }
}
