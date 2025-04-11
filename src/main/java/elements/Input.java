package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input {

    WebDriver driver;
    String label;
    public String inputLocator = "//*[@id='%s']";
    String INPUT_XPATH = "//*[contains(text(), '%s')]/ancestor::div[contains(@part, 'input-text')]//input";
    String TEXTAREA_XPATH = "//*[contains(text(), '%s')]/ancestor::*[contains(@slot, 'inputField')]//textarea";
    String INPUT_XPATH_ACCOUNT = "//label[contains(text(),'%s')]/ancestor::lightning-lookup[contains(@class,'slds-form-element')]//input";

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    /**
     * This is texting value in the field
     *
     * @return
     */
    public void writeTextToInput(String text) {
        driver.findElement(By.xpath(String.format(inputLocator, label))).sendKeys(text);
    }

    /**
     * This is texting message in the item
     *
     * @return
     */
    public void writeTextToTextarea(String text) {
        driver.findElement(By.xpath(String.format(TEXTAREA_XPATH, label))).sendKeys(text);
    }

    /**
     * This is texting value in the dropdown field
     *
     * @return
     */
    public void writeTextInDropdownField(String text) {
        driver.findElement(By.xpath(String.format(INPUT_XPATH_ACCOUNT, label))).sendKeys(text);
    }
}
