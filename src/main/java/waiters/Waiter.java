package waiters;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class Waiter {

    /**
     * This waits for downloaded page
     *
     * @return
     */
    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return" +
                        " document.readyState").toString().equals("complete");
            }
        };
    }

    /**
     * This waits for visible element on the page
     *
     * @return
     */
    public void waitForPageOpened(By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    /**
     * This checks enabled button
     *
     * @return
     */
    public void waitForButtonClickable(By element, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Ошибка при отображении кнопки: " + e.getMessage());
        }
    }
}
