package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input {

    WebDriver driver;
    String label;

    private static final String INPUT_SEARCH_XPATH = "//label[contains(text(),'%s')]/following::input[@name='%s']";
    private static final String TEXTAREA_XPATH = "//body[contains(@aria-label,'%s')]";
    private static final String INPUT_XPATH = "//*[@id='%s']";

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
        driver.findElement(By.xpath(String.format(INPUT_XPATH, label))).sendKeys(text);
    }

    /**
     * This is texting value in the field
     *
     * @return
     */
    public void writeTextToSearchField(String inputName, String text) {
        driver.findElement(By.xpath(String.format(INPUT_SEARCH_XPATH, label, inputName))).sendKeys(text);
    }

    /**
     * This is texting message in the item
     *
     * @return
     */
    public void writeTextToTextarea(String text) {
        driver.findElement(By.xpath(String.format(TEXTAREA_XPATH, label))).sendKeys(text);
    }
}
