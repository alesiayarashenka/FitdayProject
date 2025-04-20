package tests;

import entity.User;

public class Preconditions extends BaseTest {

    protected static final User userWithEmptyUserName = User.builder()
          .password(System.getenv("password"))
//            .password(PASSWORD)
            .username("")
            .build();

    protected static final User userWithEmptyPassword = User.builder()
            .password("")
          .username(System.getenv("username"))
//            .username(USER)
            .build();

    protected static final User userWithIncorrectFields = User.builder()
            .password("werwer")
            .username("efwfwe")
            .build();

    protected static final User userSuccessLogin = User.builder()
          .password(System.getenv("password"))
          .username(System.getenv("username"))
//            .username(USER)
//            .password(PASSWORD)
            .build();
}
