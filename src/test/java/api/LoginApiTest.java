package api;

import com.george.taf.utils.Responses;
import com.george.taf.utils.Utils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.george.taf.data.ApiResponseObjects.*;


public class LoginApiTest {
    @Test
    @DisplayName("Check Blocked LogIn")
    public void test1() {
        String username = "test@gmail.com";
        String password = Utils.randomPasswordGenerator(10);

        Response response = Responses.getLoginResponse(username, password);

        Assertions.assertEquals(
                expectedAccountLockedLogIn(),
                actualLogInError(response)
        );
    }

    @Test
    @DisplayName("Check LogIn without e-mail")
    public void test2() {
        String username = "";
        String password = Utils.randomPasswordGenerator(10);

        Response response = Responses.getLoginResponse(username, password);

        Assertions.assertEquals(
                expectedInvalidUsernameLogin(),
                actualLogInError(response)
        );
    }

    @Test
    @DisplayName("Check LogIn without password")
    public void test3() {
        String username = Utils.randomEmailGenerator(10);
        String password = "";

        Response response = Responses.getLoginResponse(username, password);

        Assertions.assertEquals(
                expectedInvalidPasswordLogin(),
                actualLogInError(response)
        );
    }

    @Test
    @DisplayName("Check unrecognized LogIn")
    public void test4() {
        String username = "test";
        String password = Utils.randomPasswordGenerator(10);

        Response response = Responses.getLoginResponse(username, password);

        Assertions.assertEquals(
                expectedInvalidCredentialLogin(),
                actualLogInError(response)
        );
    }
}
