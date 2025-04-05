package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {

    WebDriver driver;

    public Button(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(WebElement webElement) {
        webElement.click();
    }
}
