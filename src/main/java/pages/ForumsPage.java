package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ForumsPage extends HeaderPage {
    private static final String TAB_FORUM_XPATH = "//div[@class='trow tmenu text-center']/child::div/a[contains(text(),'%s')]";

    public ForumsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This get list of tabs
     *
     * @return
     */
     public List<WebElement> getForumTabs(String... tabName) {
        ArrayList<WebElement> tabList = new ArrayList<>();
        List<WebElement> optionsName = driver.findElements(By.xpath(String.format(TAB_FORUM_XPATH, tabName)));
        for (WebElement webElement : optionsName) {
            tabList.add(webElement);
        }
        return tabList;
    }
}
