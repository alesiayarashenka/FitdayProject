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
import pages.*;
import steps.*;
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
    protected HeaderPage headerPage;
    protected SubscribePage subscribePage;
    protected SubscribeSteps subscribeSteps;
    protected ThreadSteps threadSteps;
    protected ThreadPage threadPage;
    protected HeaderSteps headerSteps;
    protected ControlPanelPage controlPanelPage;
    protected UserSettingsSteps userSettingsSteps;
    protected ForumSteps forumSteps;
    protected SearchForumSteps searchForumSteps;
    protected SearchForumPage searchForumPage;
    protected SearchResultsPage searchResultsPage;
    protected SearchResultSteps searchResultSteps;
    protected UserInformationPage userInformationPage;
    protected UserInformationSteps userInformationSteps;
    protected SetPostSteps setPostSteps;
    protected SetPostPage setPostPage;
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
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        initPages();
    }

    public void initPages() {
        loginPage = new LoginPage(driver);
        forumsPage = new ForumsPage(driver);
        loginSteps = new LoginSteps(driver);
        headerPage = new HeaderPage(driver);
        subscribePage = new SubscribePage(driver);
        subscribeSteps = new SubscribeSteps(driver);
        threadSteps = new ThreadSteps(driver);
        threadPage = new ThreadPage(driver);
        headerSteps = new HeaderSteps(driver);
        controlPanelPage = new ControlPanelPage(driver);
        userSettingsSteps = new UserSettingsSteps(driver);
        forumSteps = new ForumSteps(driver);
        searchForumSteps = new SearchForumSteps(driver);
        searchForumPage = new SearchForumPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        searchResultSteps = new SearchResultSteps(driver);
        userInformationPage = new UserInformationPage(driver);
        userInformationSteps = new UserInformationSteps(driver);
        setPostPage = new SetPostPage(driver);
        setPostSteps = new SetPostSteps(driver);
    }

    /**
     * This is closing of pages
     */
    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
