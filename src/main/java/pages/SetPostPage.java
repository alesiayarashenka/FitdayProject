package pages;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SetPostPage extends HeaderPage {
    public static final By NEW_THREAD_BUTTON = By.xpath("//div[@class='flexitem']//a[contains(text(),'New Thread')]");
    public static final By PREVIEW_POST_BUTTON = By.xpath("//div[@class='text-center smallfont']/child::input[@value='Preview Post']");
    public static final By IFRAIME_XPATH = By.xpath("//iframe[contains(@title,'Editor')]");
    public static final String ICON_XPATH = "//div[@class='tcell']/input[contains(@value,'%s')]";
    public static final String ICON_TITLE_XPATH = "//div[@class='smallfont']/img[@title='%s']";
    public static final By PREVIEW_XPATH = By.xpath("//*[contains(text(),'Preview')]");
    public static final By PREVIEW_MESSAGE_XPATH = By.xpath("//div[@class='tcell alt1']");

    public SetPostPage(WebDriver driver) {
        super(driver);
    }

    /**
     * This inits replay to thread
     *
     * @return
     */
    public SetPostPage initPostNewThread() {
        waiter.waitForButtonClickable((NEW_THREAD_BUTTON), driver);
        new Button(driver).clickButton(driver.findElement(NEW_THREAD_BUTTON));
        waiter.waitForPageOpened((PREVIEW_POST_BUTTON), driver);
        return this;
    }

    /**
     * This fills replay form
     *
     * @return
     */
    public SetPostPage fillPostInformationField(String title, String text, String value) {
        new Input(driver, "subject").writeTextToInput(title);
        driver.switchTo().frame(driver.findElement(IFRAIME_XPATH));
        new Input(driver, "Editor").writeTextToTextarea(text);
        driver.switchTo().defaultContent();
        new Checkbox(driver).setCheckboxValue((By.xpath(String.format(ICON_XPATH, value))), true);
        new Checkbox(driver).isCheckBoxChecked(driver.findElement(By.xpath(String.format(ICON_XPATH, value))));
        new Button(driver).clickButton(driver.findElement(PREVIEW_POST_BUTTON));
        waiter.waitForPageOpened((PREVIEW_XPATH), driver);
        return this;
    }

    /**
     * This checks text in added message
     *
     * @return
     */
    public String getTextMessage() {
        return driver.findElement(PREVIEW_MESSAGE_XPATH).getText();
    }

    /**
     * This checks icon in message is displayed
     *
     * @return
     */
    public boolean IsIconTitleDisplayed(String value) {
        return driver.findElement((By.xpath(String.format(ICON_TITLE_XPATH, value)))).isDisplayed();
    }
}
