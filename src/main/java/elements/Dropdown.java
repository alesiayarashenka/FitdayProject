package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dropdown {

    WebDriver driver;

    private static final String USER_DROPDOWN_XPATH = "//*[@class='tools-title-username']";
    private static final String USER_DROPDOWN_OPTION_XPATH = "//ul[contains(@class,'f-dropdown open')]//*[contains(text(),'%s')]";

    public Dropdown(WebDriver driver) {
        this.driver = driver;

    }

    /**
     * This chooses option from dropdown
     *
     * @return
     */
    public void userSelectOption(String option) {
        driver.findElement(By.xpath(String.format(USER_DROPDOWN_XPATH))).click();
        driver.findElement(By.xpath(String.format(USER_DROPDOWN_OPTION_XPATH, option))).click();
    }
}
