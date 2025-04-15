package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SubscribePage;
import pages.ThreadPage;

public class SubscribeSteps extends BaseSteps {
    private HeaderPage headerPage;
    private SubscribePage subscribePage;
    private ThreadPage threadPage;

    public SubscribeSteps(WebDriver driver) {
        headerPage = new HeaderPage(driver);
        subscribePage = new SubscribePage(driver);
        threadPage = new ThreadPage(driver);
    }

    @Step("Select subscribe option and direct to subscription page")
    public SubscribeSteps directToSubscriptionPage(String user) {
        headerPage.isForumsOpened(user);
        headerPage.selectUsersSubscriptionPage("Subscribed Threads", "Subscriptions");
        return this;
    }

    @Step("Check unsubscribe message in the field")
    public SubscribeSteps checkSubscribeMessageInField(String expectedResult) {
        Assert.assertEquals(subscribePage.getNotExistedUserSubscriptionText(), expectedResult);
        return this;
    }

    @Step("Add subscription to thread")
    public SubscribeSteps addSubscriptionToThread() {
        threadPage.subscribeToThread();
        subscribePage.subscribeToThreadOnSubscribePage();
        subscribePage.getMessageSubscriptionText();
        return this;
    }

    @Step("Check subscription message")
    public SubscribeSteps checkSubscriptionMessage(String expectedResult) {
        Assert.assertEquals(subscribePage.getMessageSubscriptionText(), expectedResult);
        return this;
    }

    @Step("Open subscribe page")
    public SubscribeSteps openSubscribePage(String pageName) {
        subscribePage.openPage(IConstants.SUBSCRIPTION_PAGE_URL);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Open subscription list on the subscription page")
    public SubscribeSteps openAddedSubscription(String dropdownName) {
        subscribePage.checkAddedSubscriptionOption(dropdownName);
        return this;
    }

    @Step("Check added subscription in subscription list")
    public SubscribeSteps checkAddedSubscription(String dropdownOption, String expectedResult) {
        Assert.assertEquals(subscribePage.getSubscriptionFolderText(dropdownOption), expectedResult);
        return this;
    }

    @Step("Chose subscribed thread in subscription folder")
    public SubscribeSteps choseCheckboxSubscription() {
        subscribePage.selectCheckboxForDeleteSubscription();
        subscribePage.checkboxForDeleteSubscriptionIsSelected();
        return this;
    }

    @Step("Delete subscription from subscription folder")
    public SubscribeSteps deleteSubscriptionFolder(String dropdownName, String option) {
        subscribePage.deleteChooseFolderFromSubscription(dropdownName, option);
        return this;
    }
}
