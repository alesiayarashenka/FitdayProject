package pages;

import elements.Button;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserInformationPage extends BasePage {
    private static final String USER_EDITE_PROFILE_BUTTON = "//*[contains(text(),'%s')]/a";
    private static final By SAVE_INFORMATION_FORM_BUTTON = By.xpath("//*[@id='submitbutton']");
    private static final String INFORMATION_FIELD_XPATH = "//*[@id='%s']";

    public UserInformationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This chooses item for filling in user information form
     *
     * @return
     */
    public UserInformationPage chooseUserInformationValue(String option) {
        waiter.waitForButtonClickable((By.xpath(String.format(USER_EDITE_PROFILE_BUTTON, option))), driver);
        new Button(driver).clickButton(driver.findElement(By.xpath(String.format(USER_EDITE_PROFILE_BUTTON, option))));
        return this;
    }

    /**
     * This checks user information fields are opened
     *
     * @return
     */
    public void isProfileInformationFieldOpened() {
        waiter.waitForPageOpened((SAVE_INFORMATION_FORM_BUTTON), driver);
    }

    /**
     * This fills biography information field
     *
     * @return
     */
    public UserInformationPage fillBiographyInformationField(String value) {
        new Input(driver, "ctb_field1").writeTextToInput(value);
        return this;
    }

    /**
     * This checks added information in filled form
     *
     * @return
     */
    public boolean checkProfileFieldText(String fieldName, String value) {
        return driver.findElement(By.xpath(String.format(INFORMATION_FIELD_XPATH, fieldName))).getAttribute("innerText").contentEquals(value);
    }

    /**
     * This clears information field
     *
     * @return
     */
    public UserInformationPage clearInformationField(String fieldName) {
        driver.findElement(By.xpath(String.format(INFORMATION_FIELD_XPATH, fieldName))).clear();
        return this;
    }

    /**
     * This saves information in field
     *
     * @return
     */
    public UserInformationPage saveChangesInUserInformationField(String option) {
        new Button(driver).clickButton(driver.findElement(SAVE_INFORMATION_FORM_BUTTON));
        waiter.waitForButtonClickable((By.xpath(String.format(USER_EDITE_PROFILE_BUTTON, option))), driver);
        return this;
    }
}
