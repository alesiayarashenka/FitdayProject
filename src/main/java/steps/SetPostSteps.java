package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SetPostPage;
import pages.SubscribePage;

public class SetPostSteps extends BaseSteps {
    private SetPostPage setPostPage;
    private HeaderPage headerPage;

    public SetPostSteps(WebDriver driver) {
        setPostPage = new SetPostPage(driver);
        headerPage = new HeaderPage(driver);
    }

    @Step("Open forum 'diets' page")
    public SetPostSteps openForumDietsPage(String pageName) {
        setPostPage.openPage(IConstants.FORUM_DIETS_PAGE_URL);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Fill replay form")
    public SetPostSteps fillReplayForm(String title, String text, String value) {
        setPostPage.initPostNewThread();
        setPostPage.fillPostInformationField(title, text, value);
        Assert.assertTrue(setPostPage.getTextMessage().contains(title));
        Assert.assertTrue(setPostPage.getTextMessage().contains(text));
        return this;
    }

    @Step("Check added message icon is displayed")
    public SetPostSteps checkMessageIcon(String value) {
        Assert.assertTrue(setPostPage.IsIconTitleDisplayed(value));
        return this;
    }
}
