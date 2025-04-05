package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dropdown {

    WebDriver driver;
    String label;

    private static final String ACCOUNT_DROPDOWN_XPATH = "//*[contains(text(), '%s')]/ancestor::*[contains(@slot, 'inputField')]//button";
    private static final String ACCOUNT_DROPDOWN_OPTION_XPATH = "//*[contains(@title, '%s')]";

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void accountSelectOption(String option) {
        driver.findElement(By.xpath(String.format(ACCOUNT_DROPDOWN_XPATH, label))).click();
        driver.findElement(By.xpath(String.format(ACCOUNT_DROPDOWN_OPTION_XPATH, option))).click();
    }
}
