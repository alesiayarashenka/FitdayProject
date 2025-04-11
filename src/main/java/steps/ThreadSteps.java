package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.ThreadPage;

public class ThreadSteps extends BaseSteps {

    private ThreadPage threadPage;
    private HeaderPage headerPage;

    public ThreadSteps(WebDriver driver) {
        threadPage = new ThreadPage(driver);
        headerPage = new HeaderPage(driver);
    }

    @Step("Open forum WEIGHT LOSS, choose subforum")
    public ThreadSteps chooseForumToSubscribe(String forumName, String threadName) {
        threadPage.openPage(IConstants.THREAD_WEIGHT_LOSS_PAGE_URL);
        headerPage.isPageOpened(forumName);
        threadPage.directToForum(threadName);
        headerPage.isPageOpened(threadName);
        return this;
    }

    @Step("Delete subscription to thread")
    public ThreadSteps deleteSubscriptionToThread(String threadName) {
        headerPage.isPageOpened(threadName);
        threadPage.unsubscribeFromThread();
        threadPage.getMessageDeleteSubscription();
        return this;
    }

    @Step("Check deleted subscription message")
    public ThreadSteps checkDeleteSubscriptionMessage(String expectedResult) {
        Assert.assertEquals(threadPage.getMessageDeleteSubscription(), expectedResult);
        return this;
    }

    @Step("Open forum WEIGHT LOSS page, choose thread")
    public ThreadSteps chooseThreadToSubscribe(String subForumName, String threadName) {
        headerPage.isPageOpened(subForumName);
        threadPage.directToThread(threadName);
        headerPage.isPageOpened(threadName);
        return this;
    }
}
