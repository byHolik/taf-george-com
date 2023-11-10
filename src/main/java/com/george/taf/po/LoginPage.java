package com.george.taf.po;

import com.george.taf.utils.Driver;
import com.george.taf.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static com.george.taf.data.Locators.*;

public class LoginPage {
    public static final Logger logger = LogManager.getLogger();

    public LoginPage() {
        logger.info("Open Log In page");
        new HomePage();
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(LOG_IN_HELP_LINK)))
                .click();
        for(String winHandle : Driver.getDriver().getWindowHandles()){
            Driver.getDriver().switchTo().window(winHandle);
        }
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_HOME_SITE_LINK)))
                .click();
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_SIGN_IN_BTN)))
                .click();
    }

    public LoginPage inputUserName() {
        logger.info("Input random e-mail");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        LOG_IN_USER_NAME_INPUT)))
                .sendKeys(Utils.randomEmailGenerator(10));
        return  this;
    }
    public LoginPage inputUserName(String username) {
        logger.info("Input user name - " + username);
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_USER_NAME_INPUT)))
                .sendKeys(username);
        return  this;
    }
    public LoginPage inputPassword() {
        logger.info("Input password");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(LOG_IN_PASSWORD_ID)))
                .sendKeys(Utils.randomEmailGenerator(10));
        return  this;
    }
    public LoginPage clickSignInButton() {
        logger.info("Click Sign In button");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_SUBMIT)))
                .submit();
        return this;
    }
    public String getActualAlertNessage() {
        logger.info("Get alert message");
        return  new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_ALERT)))
                .getText();
    }
    public String getActualPageName() {
        logger.info("Get page name");
        return  new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOG_IN_PAGE_NAME)))
                .getText();
    }
}



