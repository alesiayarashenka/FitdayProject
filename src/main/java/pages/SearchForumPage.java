package pages;

import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class SearchForumPage extends BasePage {
    private static final By VALUE_LIST_DROPDOWN_SEARCH = By.xpath("//div[@id='tag_add_menu']");
    private static final String VALUE_DROPDOWN_XPATH = "//*[contains(text(),' %s')]";
    private static final String DROPDOWN_XPATH = "//select[@name='%s']";
    private static final By SUBMIT_BUTTON = By.xpath("//input[@value='Search Now']");
    private static final By REPLY_LIMIT_FIELD = By.xpath("//input[@name='replylimit']");

    public SearchForumPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This set tag in field by search
     *
     * @return
     */
    public SearchForumPage writeTagValueInField(String text) {
        new Input(driver, "Tag:").writeTextToSearchField("tag", text);
        log.info("In tag field has been written {}", text);
        return this;
    }

    /**
     * This set keyword in field by search
     *
     * @return
     */
    public SearchForumPage writeKeywordValueInField(String text) {
        new Input(driver, "Keyword(s):").writeTextToSearchField("query", text);
        log.info("In keyword field has been written {}", text);
        return this;
    }

    /**
     * This waits for display result options
     *
     * @return
     */
    public void waitDropdownSearchDisplayed() {
        waiter.waitForPageOpened((VALUE_LIST_DROPDOWN_SEARCH), driver);
        log.info("Search dropdown values are displayed");
    }

    /**
     * This chooses option in list of tags
     *
     * @return
     */
    public SearchForumPage chooseValueInDropdownList(String option) {
        new Button(driver).clickButton(driver.findElement(By.xpath(String.format(VALUE_DROPDOWN_XPATH, option))));
        return this;
    }

    /**
     * This return all dropdown options
     *
     * @return
     */
    public List<String> getOptionsListInDropdown() {
        ArrayList<String> forumList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(VALUE_LIST_DROPDOWN_SEARCH);
        for (WebElement webElement : optionsName) {
            forumList.add(webElement.getText());
        }
        return forumList;
    }

    /**
     * This chooses value in dropdown
     *
     * @return
     */
    public SearchForumPage selectOptionInDropdown(String option, String value) {
        Select dropdown = new Select(driver.findElement(By.xpath(String.format(DROPDOWN_XPATH, option))));
        dropdown.selectByValue(value);
        log.info("Dropdown value {} has been selected", value);
        return this;
    }

    /**
     * This submits search form
     *
     * @return
     */
    public SearchResultsPage submitSearchForm() {
        waiter.waitForButtonClickable((SUBMIT_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(SUBMIT_BUTTON));
        waiter.waitForPageLoaded();
        log.info("Search form has been submitted");
        return new SearchResultsPage(driver);
    }

    /**
     * This set replay limit to results
     *
     * @return
     */
    public SearchForumPage setReplyLimit(String valueAmount) {
        driver.findElement(REPLY_LIMIT_FIELD).clear();
        driver.findElement(REPLY_LIMIT_FIELD).sendKeys(valueAmount);
        log.info("Replay limit in {} has been selected", valueAmount);
        return this;
    }
}