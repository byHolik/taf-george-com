package ui;

import com.george.taf.data.ExpectedResults;
import com.george.taf.po.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    @DisplayName("Open LogIn Page")
    public void openLogInPageTest() {
        loginPage = new LoginPage();

        Assertions.assertEquals(
                ExpectedResults.LOG_IN_SIGN_IN_PAGE_NAME,
                loginPage.getActualPageName());
    }

    @Test
    public void logInWithoutPassword() {
        LoginPage loginPage =
                new LoginPage()
                        .inputUserName()
                        .clickSignInButton();

        Assertions.assertEquals(
                ExpectedResults.LOG_IN_NO_PASSWORD_ALERT_MESSAGE,
                loginPage.getActualAlertNessage());
    }

    @Test
    public void logInWithoutUserName() {
        LoginPage loginPage =
                new LoginPage()
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                ExpectedResults.LOG_IN_NO_USERNAME_ALERT_MESSAGE,
                loginPage.getActualAlertNessage());
    }

    @Test
    public void logInWithIncorrectEmail() {
        LoginPage loginPage =
                new LoginPage()
                        .inputUserName("test")
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                ExpectedResults.LOG_IN_EMAIL_ALERT_MESSAGE,
                loginPage.getActualAlertNessage());
    }

    @Test
    public void logInWithIncorrectPhone() {
        LoginPage loginPage =
                new LoginPage()
                        .inputUserName("123")
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                ExpectedResults.LOG_IN_PHONE_ALERT_MESSAGE,
                loginPage.getActualAlertNessage());
    }
}
