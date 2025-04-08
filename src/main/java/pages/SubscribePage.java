package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubscribePage extends HeaderPage{
    private static final String SUBSCRIPTION_ITEM_XPATH ="//div[contains(@class,'tcell alt1 smallfont')]";

    public SubscribePage(WebDriver driver) {
        super(driver);
    }

    /**
     * This is checking existing user's subscriptions
     *
     * @return
     */
    public String checkUserSubscription() {
        return driver.findElement(By.xpath(String.format(SUBSCRIPTION_ITEM_XPATH))).getText();
    }
}
