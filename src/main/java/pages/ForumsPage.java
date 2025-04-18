package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ForumsPage extends HeaderPage {
    private static final String TAB_FORUM_XPATH = "//div[@class='trow tmenu text-center']/child::div/a[contains(text(),'%s')]";

    public ForumsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking forum tabs are displayed
     *
     * @return
     */
    public void isForumTabsDisplayed(String... tabNames) {
       for(String tabName : tabNames){
           Assert.assertTrue(driver.findElement(By.xpath(String.format(TAB_FORUM_XPATH, tabName))).isDisplayed());
        }
    }
}
