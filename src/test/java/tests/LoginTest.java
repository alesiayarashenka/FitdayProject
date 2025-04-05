package tests;

import entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends Preconditions{
    public static final String INCORRECT_DATA_IN_FIELDS = "User name or password do not match";
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Login user with empty username field, check changed field color for username form")
    public void loginWithEmptyUserNameFieldTest() {
      loginSteps.loginAndWaitForPageOpened(userWithEmptyUserName);
       String colorUsernameField = loginPage.getFieldColor("#username");
       String colorPasswordField = loginPage.getFieldColor("#password");
       softAssert.assertEquals(colorUsernameField,"#ff0000");
       softAssert.assertEquals(colorPasswordField,"#ced4da");
       softAssert.assertNotEquals(colorUsernameField,colorPasswordField);
       softAssert.assertAll();
    }

    @Test(description = "Login user with empty password field, check changed field color for password form")
    public void loginWithEmptyPasswordTest() {
        loginSteps.loginAndWaitForPageOpened(userWithEmptyPassword);
        String colorUsernameField = loginPage.getFieldColor("#username");
        String colorPasswordField = loginPage.getFieldColor("#password");
        softAssert.assertEquals(colorUsernameField,"#ced4da");
        softAssert.assertEquals(colorPasswordField,"#ff0000");
        softAssert.assertNotEquals(colorUsernameField,colorPasswordField);
        softAssert.assertAll();
    }

    @Test(description = "Login user with incorrect email and password, check message")
    public void loginWithIncorrectFieldsTest() {
        loginSteps.loginAndWaitForPageOpened(userWithIncorrectFields);
        Assert.assertEquals(loginPage.getErrorMessageTest(), INCORRECT_DATA_IN_FIELDS);
    }

    @Test(description = "Login user with correct username and password")
    public void successfulLoginTest() {
       loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
       forumsPage.isOpened(USER);
    }
}
