package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkbox {
    WebDriver driver;

    public Checkbox(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This clicks at the checkbox
     *
     * @return
     */
    public void clickCheckbox(WebElement webElement) {
        webElement.click();
    }

    /**
     * This checks the checkbox is selected
     *
     * @return
     */
    public boolean checkboxIsSelected(WebElement webElement) {
        return webElement.isSelected();
    }
}
