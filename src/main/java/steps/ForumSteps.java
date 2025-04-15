package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ForumsPage;

public class ForumSteps extends BaseSteps{
    private ForumsPage forumsPage;

    public ForumSteps(WebDriver driver) {
       forumsPage  = new ForumsPage(driver);
    }

    @Step("Check existing header tabs")
    public ForumSteps checkExistingForumTabs(String... tab) {
        forumsPage.isForumTabsDisplayed(tab);
        return this;
    }
}
