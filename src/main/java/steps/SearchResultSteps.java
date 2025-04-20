package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.HeaderPage;
import pages.SearchResultsPage;

public class SearchResultSteps extends BaseSteps {
    private SearchResultsPage searchResultsPage;
    private HeaderPage headerPage;

    public SearchResultSteps(WebDriver driver) {
        searchResultsPage = new SearchResultsPage(driver);
        headerPage = new HeaderPage(driver);
    }

    @Step("Open search results page")
    public SearchResultSteps isSearchResultsPageOpened(String pageName) {
        headerPage.isPageOpened(pageName);
        return this;
    }

    @Step("Check query option in dropdown search result")
    public boolean getForumNameInResults(String option) {
        return searchResultsPage.getResultsValue().contains(option);
    }
}
