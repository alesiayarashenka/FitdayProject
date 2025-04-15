package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends Preconditions implements ITestConstants {

    @Test(description = "Login user, direct to search page, set search values, check results")
    public void searchByTagTest() {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        headerSteps.checkWelcomeMessageInHeader(USER);
        searchForumSteps.openSearchForumPage(SEARCH_PAGE_NAME)
                .fillSearchForumForm("health", "and fitness");
        searchResultSteps.isSearchResultsPageOpened(SEARCH_RESULTS_PAGE_NAME);
        Assert.assertTrue(searchResultSteps.getForumNameInResults("Nutrition & labeling"));
    }
}
