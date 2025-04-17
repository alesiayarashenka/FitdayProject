package tests;

import entity.User;

public class Preconditions extends BaseTest {

    protected static final User userWithEmptyUserName = User.builder()
            .password(PASSWORD)
            .username("")
            .build();

    protected static final User userWithEmptyPassword = User.builder()
            .password("")
            .username(USER)
            .build();

    protected static final User userWithIncorrectFields = User.builder()
            .password("werwer")
            .username("efwfwe")
            .build();

    protected static final User userSuccessLogin = User.builder()
            .password(PASSWORD)
            .username(USER)
            .build();
}
