package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{
    private static final By RESULT_NAME_XPATH = By.xpath("//div[contains(@class,'text-left')]/a");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This checks all options in dropdown
     *
     * @return
     */
    public List<String> getResultsValue() {
        ArrayList<String> forumList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(RESULT_NAME_XPATH);
        for (WebElement webElement : optionsName) {
            forumList.add(webElement.getText());
        }
        return forumList;
    }
}
