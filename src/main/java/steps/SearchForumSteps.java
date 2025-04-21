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
    public SearchForumSteps openSearchForumPage(String pageName) {
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
    public void fillSearchForumForm(String tagName, String titleName, String value1, String amount, String value2, String title) {
        searchForumPage.writeTagValueInField(tagName);
        searchForumPage.waitDropdownSearchDisplayed();
        checkOptionsInDropdownList(tagName);
        searchForumPage.chooseValueInDropdownList(titleName);
        searchForumPage.writeKeywordValueInField(tagName)
                .selectOptionInDropdown(value1, amount)
                .selectOptionInDropdown(value2, title);
        searchForumPage.setReplyLimit(amount);
        searchForumPage.submitSearchForm();
    }
}
