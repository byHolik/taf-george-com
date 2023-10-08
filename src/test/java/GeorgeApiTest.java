import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GeorgeApiTest {
    @Test
    @DisplayName("George, LogIn Blocked")
    public void test1() {
        String username = "test@gmail.com";
        String password = "qweqwe";
        String body = "{" +
                "\"username\":\"" + username + "\"," +
                "\"redirect_uri\":\"https://global.direct.asda.com/george/clothing/10,default,sc.html\"," +
                "\"rememberme\":\"N\"," +
                "\"method\":\"authentication\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        String expectedStatus = "ERROR";
        String expectedErrorCode = "ACCOUNT_LOCKED";
        String expectedErrorDescription = "Sorry, for your security, your account has been locked. Please click 'forgotten password' to unlock your account.";

        RestAssured
                .given().body(body)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .when().post("https://www.asda.com/api/v1/login?requestorigin=george")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("status", equalTo(expectedStatus))
                .body("errorCode", equalTo(expectedErrorCode))
                .body("errorDescription", equalTo(expectedErrorDescription));
    }

    @Test
    @DisplayName("George, LogIn without e-mail")
    public void test2() {
        String username = "";
        String password = "qweqwe";
        String body = "{" +
                "\"username\":\"" + username + "\"," +
                "\"redirect_uri\":\"https://global.direct.asda.com/george/clothing/10,default,sc.html\"," +
                "\"rememberme\":\"N\"," +
                "\"method\":\"authentication\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        String expectedStatus = "ERROR";
        String expectedErrorCode = "INVALID_USERNAME";
        String expectedErrorDescription = "Please enter your email address";

        RestAssured
                .given().body(body)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .when().post("https://www.asda.com/api/v1/login?requestorigin=george")
                .then()
                .assertThat()
                .body("status", equalTo(expectedStatus))
                .body("errorCode", equalTo(expectedErrorCode))
                .body("errorDescription", equalTo(expectedErrorDescription));
    }

    @Test
    @DisplayName("George, LogIn without password")
    public void test3() {
        String username = "random.test.email.name@gmail.com";
        String password = "";
        String body = "{" +
                "\"username\":\"" + username + "\"," +
                "\"redirect_uri\":\"https://global.direct.asda.com/george/clothing/10,default,sc.html\"," +
                "\"rememberme\":\"N\"," +
                "\"method\":\"authentication\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        String expectedStatus = "ERROR";
        String expectedErrorCode = "INVALID_PASSWORD";
        String expectedErrorDescription = "Please enter your password";

        RestAssured
                .given().body(body)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .when().post("https://www.asda.com/api/v1/login?requestorigin=george")
                .then()
                .assertThat()
                .body("status", equalTo(expectedStatus))
                .body("errorCode", equalTo(expectedErrorCode))
                .body("errorDescription", equalTo(expectedErrorDescription));
    }
}
