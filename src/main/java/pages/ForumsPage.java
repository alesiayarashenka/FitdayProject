package pages;

import entity.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ForumsPage extends BasePage{
    private static final String WELCOME_MEMBER_XPATH = "//div[contains(@class,'member-form')]/*[contains(text(),'Welcome, ')]/*[contains(text(),'%s')]";

    public ForumsPage(WebDriver driver) {
        super(driver);
    }

    public String isOpened(String user) {
        return driver.findElement(By.xpath(String.format(WELCOME_MEMBER_XPATH, user))).getText();
    }
}
