package ui;

import com.george.taf.data.UiExpectedResults;
import com.george.taf.po.LoginPage;
import com.george.taf.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    @Test
    @DisplayName("Open LogIn Page")
    public void openLogInPageTest() {
        Utils.logger.info("Start LogIn Test - 'Open LogIn Page'");
        LoginPage loginPage = new LoginPage();

        Assertions.assertEquals(
                UiExpectedResults.LOG_IN_SIGN_IN_PAGE_NAME,
                loginPage.getPageName());
    }

    @Test
    @DisplayName("LogIn W/O Password Test")
    public void logInWithoutPassword() {
        Utils.logger.info("Start LogIn Test - 'LogIn W/O Password Test'");
        LoginPage loginPage = new LoginPage()
                        .inputUserName()
                        .clickSignInButton();

        Assertions.assertEquals(
                UiExpectedResults.LOG_IN_NO_PASSWORD_ALERT_MESSAGE,
                loginPage.getAlertMessage());
    }

    @Test
    @DisplayName("LogIn W/O Username Test")
    public void logInWithoutUserName() {
        Utils.logger.info("Start LogIn Test - 'LogIn W/O Username Test'");
        LoginPage loginPage = new LoginPage()
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                UiExpectedResults.LOG_IN_NO_USERNAME_ALERT_MESSAGE,
                loginPage.getAlertMessage());
    }

    @Test
    @DisplayName("LogIn With Incorrect Email Test")
    public void logInWithIncorrectEmail() {
        Utils.logger.info("Start LogIn Test - 'LogIn With Incorrect Email Test'");
        LoginPage loginPage = new LoginPage()
                        .inputUserName("test")
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                UiExpectedResults.LOG_IN_EMAIL_ALERT_MESSAGE,
                loginPage.getAlertMessage());
    }

    @Test
    @DisplayName("LogIn With Incorrect Phone Test")
    public void logInWithIncorrectPhone() {
        Utils.logger.info("Start LogIn Test - 'LogIn With Incorrect Phone Test'");
        LoginPage loginPage = new LoginPage()
                        .inputUserName("123")
                        .inputPassword()
                        .clickSignInButton();

        Assertions.assertEquals(
                UiExpectedResults.LOG_IN_PHONE_ALERT_MESSAGE,
                loginPage.getAlertMessage());
    }
}
