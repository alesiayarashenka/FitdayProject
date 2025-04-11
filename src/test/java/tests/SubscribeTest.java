package tests;

import org.testng.annotations.Test;

import static tests.Preconditions.userSuccessLogin;

public class SubscribeTest extends BaseTest implements ITestConstants {

    @Test(description = "Login user, check not existing subscription")
    public void checkUserSubscriptionTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        subscribeSteps.directToSubscriptionPage(USER)
                      .checkSubscribeMessageInField(UNSUBSCRIBE_USER_MESSAGE);
    }

    @Test(description = "Login user, subscribe to forum, delete subscription in forum")
    public void subscribeAndDeleteSubscriptionTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        threadSteps.chooseForumToSubscribe(FORUM_WEIGHT_LOSS_PAGE_NAME, SUBFORUM_DIETS_PAGE_NAME);
        subscribeSteps.addSubscriptionToThread()
                      .checkSubscriptionMessage(SUCCESSFUL_ADD_SUBSCRIPTION_FORUM_MESSAGE);
        threadSteps.deleteSubscriptionToThread(SUBFORUM_DIETS_PAGE_NAME)
                   .checkDeleteSubscriptionMessage(SUCCESSFUL_DELETE_SUBSCRIPTION_MESSAGE);
    }

    @Test(description = "Login user, subscribe to thread, delete subscription in subscription page")
    public void subscribeAndDeleteSubscriptionInSubscriptionPageTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        threadSteps.chooseForumToSubscribe(FORUM_WEIGHT_LOSS_PAGE_NAME, SUBFORUM_DIETS_PAGE_NAME)
                   .chooseThreadToSubscribe(SUBFORUM_DIETS_PAGE_NAME, THREAD_PAGE_NAME);
        subscribeSteps.addSubscriptionToThread()
                      .checkSubscriptionMessage(SUCCESSFUL_ADD_SUBSCRIPTION_THREAD_MESSAGE)
                      .openSubscribePage(SUBSCRIPTION_PAGE_NAME)
                      .openAddedSubscription("folderid")
                      .checkAddedSubscription("0", ADDED_THREAD_IN_SUBSCRIPTION_FOLDER)
                      .choseCheckboxSubscription()
                      .deleteSubscriptionFolder("what", "delete")
                      .checkSubscribeMessageInField(UNSUBSCRIBE_USER_MESSAGE);
    }
}
