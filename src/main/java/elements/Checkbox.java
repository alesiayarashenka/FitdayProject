package elements;

import org.openqa.selenium.By;
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
    public void setCheckboxValue(By element, boolean selected) {
        WebElement checkbox = driver.findElement(element);
        if (selected && !checkbox.isSelected()) {
            checkbox.click();
        } else if (!selected && checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * This checks the checkbox is selected
     *
     * @return
     */
    public boolean isCheckBoxChecked(WebElement webElement) {
        return webElement.isSelected();
    }
}
