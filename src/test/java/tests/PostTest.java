package tests;

import org.testng.annotations.Test;

public class PostTest extends Preconditions implements ITestConstants {

    @Test(description = "Login user, direct to forum, add replay to thread")
    public void addReplayToThreadTest() throws InterruptedException {
        loginSteps.loginAndWaitForPageOpened(userSuccessLogin);
        headerSteps.checkWelcomeMessageInHeader(System.getenv("username"));
//        headerSteps.checkWelcomeMessageInHeader(USER); //---for local
        setPostSteps.openForumDietsPage(SUBFORUM_DIETS_PAGE_NAME)
                .fillReplayForm("Title", "text", "11")
                .checkMessageIcon("Red face");
    }
}
