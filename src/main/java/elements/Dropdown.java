package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Dropdown {
    private static final String USER_DROPDOWN_XPATH = "//*[@class='tools-title-username']";
    private static final String USER_DROPDOWN_OPTION_XPATH = "//ul[contains(@class,'f-dropdown open')]//*[contains(text(),'%s')]";
    private static final String SUBSCRIPTION_DROPDOWN_XPATH = "//select[@name='%s']";
    private static final String SUBSCRIPTION_DROPDOWN_OPTION_XPATH = "//option[@value='%s']";

    WebDriver driver;

    public Dropdown(WebDriver driver) {
        this.driver = driver;

    }

    /**
     * This opens header user dropdown
     *
     * @return
     */
    public void userOpenHeaderDropdown() {
        driver.findElement(By.xpath(String.format(USER_DROPDOWN_XPATH))).click();
    }

    /**
     * This chooses subscription option in header dropdown
     *
     * @return
     */
    public void userSelectSubscrOption(String option) {
        userOpenHeaderDropdown();
        driver.findElement(By.xpath(String.format(USER_DROPDOWN_OPTION_XPATH, option))).click();
    }

    /**
     * This clicks at subscription dropdown field
     *
     * @return
     */
    public void clickAtDropdownField(String option) {
        driver.findElement(By.xpath(String.format(SUBSCRIPTION_DROPDOWN_XPATH, option))).click();
    }

    /**
     * This gets text in subscription dropdown value
     *
     * @return
     */
    public String getTextInDropdownOption(String option) {
        try {
            log.info("Getting subscription thread message text.");
            return driver.findElement(By.xpath(String.format(SUBSCRIPTION_DROPDOWN_OPTION_XPATH, option))).getText();
        } catch (Exception e) {
            log.error("Failed to get subscription thread message text.", e);
            return "";
        }
    }

    /**
     * This chooses option in subscription dropdown
     *
     * @return
     */
    public void selectOptionInDropdown(String dropdownName, String option) {
        driver.findElement(By.xpath(String.format(SUBSCRIPTION_DROPDOWN_XPATH, dropdownName))).click();
        driver.findElement(By.xpath(String.format(SUBSCRIPTION_DROPDOWN_OPTION_XPATH, option))).click();
    }
}
