package tests;

import constants.IConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ForumsPage;
import steps.LoginSteps;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest implements IConstants {

    WebDriver driver;
    protected LoginPage loginPage;
    protected ForumsPage forumsPage;
    protected LoginSteps loginSteps;
    public static String USER = PropertyReader.getProperty("username");
    public static String PASSWORD = PropertyReader.getProperty("password");

    /**
     * This is initialization of pages
     */
    @BeforeMethod
    public void initTest(ITestContext iTestContext) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        initPages();
    }

    public void initPages() {
        loginPage = new LoginPage(driver);
        forumsPage = new ForumsPage(driver);
        loginSteps = new LoginSteps(driver);
    }

    /**
     * This is closing of pages
     */
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
