package pages;

import elements.Button;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ThreadPage extends HeaderPage {
    private static final String FORUM_XPATH = "//*[strong='%s']";
    public static final By SUBSCRIBE_BUTTON = By.xpath("//div[@class='flexitem']//a[contains(text(),'Subscribe')]");
    public static final By UNSUBSCRIBE_BUTTON = By.xpath("//div[@class='flexitem']//a[contains(text(),'Subscribed')]");
    private static final String DELETE_SUBSCRIPTION_MESSAGE_IN_FORM = "//p/child::strong";
    private static final String THREAD_XPATH = "//*[contains(text(),'%s')]";

    public ThreadPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This directs to subforum on forum page
     *
     * @return
     */
    public ThreadPage directToForum(String forumName) {
        new Button(driver).clickButton(driver.findElement(By.xpath(String.format(FORUM_XPATH, forumName))));
        log.info("Direct to forum {}", forumName);
        return this;
    }

    /**
     * This directs to the thread in subforum on forum page
     *
     * @return
     */
    public ThreadPage directToThread(String threadName) {
        new Button(driver).clickButton(driver.findElement(By.xpath(String.format(THREAD_XPATH, threadName))));
        log.info("Direct to forum {}", threadName);
        return this;
    }

    /**
     * This subscribes to the thread in subforum
     *
     * @return
     */
    public SubscribePage subscribeToThread() {
        waiter.waitForButtonClickable((SUBSCRIBE_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(SUBSCRIBE_BUTTON));
        log.info("Subscribe to thread in forum page");
        return new SubscribePage(driver);
    }

    /**
     * This unsubscribes from the thread in subforum
     *
     * @return
     */
    public ThreadPage unsubscribeFromThread() {
        waiter.waitForButtonClickable((UNSUBSCRIBE_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(UNSUBSCRIBE_BUTTON));
        log.info("Unsubscribe from thread in forum page");
        return this;
    }

    /**
     * This checks message after unsubscription
     *
     * @return
     */
    public String getMessageDeleteSubscription() {
        try {
            log.info("Getting deleted subscription message text.");
            waiter.waitForPageOpened((By.xpath(String.format(DELETE_SUBSCRIPTION_MESSAGE_IN_FORM))), driver);
            return driver.findElement(By.xpath(String.format(DELETE_SUBSCRIPTION_MESSAGE_IN_FORM))).getText();
        } catch (Exception e) {
            log.error("Failed to get deleted subscription message text.", e);
            return "";
        }
    }
}
