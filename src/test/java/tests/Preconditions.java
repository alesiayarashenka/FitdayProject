package tests;

import entity.User;

public class Preconditions extends BaseTest {

    protected static final User userWithEmptyUserName = User.builder()
            .password(System.getenv("password"))
            .username("")
            .build();

    protected static final User userWithEmptyPassword = User.builder()
            .password("")
            .username(System.getenv("username"))
            .build();

    protected static final User userWithIncorrectFields = User.builder()
            .password("werwer")
            .username("efwfwe")
            .build();

    protected static final User userSuccessLogin = User.builder()
            .password(System.getenv("password"))
            .username(System.getenv("username"))
//            .password(PASSWORD) //---for local
//            .username(USER) //---for local
            .build();
}
