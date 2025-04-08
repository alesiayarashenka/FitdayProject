package tests;

import org.testng.annotations.Test;

import static tests.Preconditions.userSuccessLogin;

public class SubscribeTest extends BaseTest implements ITestConstants{

    @Test(description = "Login user, check subscription")
    public void checkUserSubscription() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        subscribeSteps.directToSubscriptionPage(USER)
                      .checkUnsubscribeMessage(UNSUBSCRIBE_USER_MESSAGE);
    }
}
