package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SubscribePage;

public class SubscribeSteps extends BaseSteps{
    private HeaderPage headerPage;
    private SubscribePage subscribePage;

    public SubscribeSteps(WebDriver driver) {
        headerPage = new HeaderPage(driver);
        subscribePage = new SubscribePage(driver);
    }
    @Step("Select subscribe option and direct to subscription page")
    public SubscribeSteps directToSubscriptionPage(String user) {
        headerPage.forumsIsOpened(user);
        headerPage.selectUsersPage("Subscribed Threads", "Subscriptions");
        return this;
    }

    @Step("Check unsubscribe message in the field")
    public SubscribeSteps checkUnsubscribeMessage(String expectedResult) {
        Assert.assertEquals(subscribePage.checkUserSubscription(), expectedResult);
        return this;
    }
}
