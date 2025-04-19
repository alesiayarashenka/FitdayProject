package steps;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SearchForumPage;

public class SearchForumSteps extends BaseSteps {
    private SearchForumPage searchForumPage;
    private HeaderPage headerPage;

    public SearchForumSteps(WebDriver driver) {
        searchForumPage = new SearchForumPage(driver);
        headerPage = new HeaderPage(driver);
    }

    @Step("Open search forum page")
    public SearchForumSteps openSearchForumPage(String pageName) throws InterruptedException {
        searchForumPage.openPage(IConstants.SEARCH_PAGE_URL);
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Check query option in dropdown search result")
    public void checkOptionsInDropdownList(String option) {
        for (String name : searchForumPage.getOptionsListInDropdown()) {
            Assert.assertTrue(name.contains(option));
        }
    }

    @Step("Set value in search fields")
    public void fillSearchForumForm(String tagName, String titleName) {
        searchForumPage.writeTagValueInField(tagName);
        searchForumPage.waitDropdownSearchDisplayed();
        checkOptionsInDropdownList(tagName);
        searchForumPage.chooseValueInDropdownList(titleName);
        searchForumPage.writeKeywordValueInField(tagName)
                .selectOptionInDropdown("titleonly", "1")
                .selectOptionInDropdown("sortby", "title");
        searchForumPage.setReplyLimit("1");
        searchForumPage.submitSearchForm();
    }
}
