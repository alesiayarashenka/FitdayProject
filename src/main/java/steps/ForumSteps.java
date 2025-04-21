package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.ForumsPage;

public class ForumSteps extends BaseSteps{
    private ForumsPage forumsPage;

    public ForumSteps(WebDriver driver) {
       forumsPage  = new ForumsPage(driver);
    }


    @Step("Check existing header tabs")
    public ForumSteps isForumTabDisplayed(String... tabValue) {
        for(WebElement tabName : forumsPage.getForumTabs(tabValue)){
            Assert.assertTrue(tabName.isDisplayed());
        }
        return this;
    }
}
