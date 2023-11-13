package com.george.taf.utils;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.george.taf.data.UiLocators.*;

public class Driver {
    public static final int SECONDS = 1;
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
/*        String userProfile = "C:\\Users\\Vova\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36";*/
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

/*          options.addArguments("Cookie=" + TestData.COOKIE);
            options.addArguments("authority=global.direct.asda.com");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("Cache-Control=no-cache");*/
/*            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--user-data-dir=/home/{username}/.config/google-chrome");
            options.addArguments("--profile-directory="+userProfile);*/
/*            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:/Users/New/User Data");
            options.addArguments("profile-directory=Profile 1");*/
/*          ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");*/

            driver = new ChromeDriver(options);
            //driver.manage().window().maximize();

            /*


                    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        //chrome://version/

        chromeOptions.addArguments("--user-data-dir=/home/{username}/.config/google-chrome");
        //load default profile
        chromeOptions.addArguments("--profile-directory=Default");
            * String userProfile= "C:\\Users\\user_name\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
ChromeOptions options = new ChromeOptions();
options.addArguments("user-data-dir="+userProfile);
options.addArguments("--start-maximized");
WebDriver driver = new ChromeDriver(options);
driver.get("http://www.google.com");
            *
            */
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pauseFluent(String xPath, int millis) {
        try {
            Utils.logger.info("Fluent pause " + millis + " ms");
            new FluentWait(Driver.getDriver())
                    .withTimeout(Duration.ofMillis(millis))
                    .pollingEvery(Duration.ofMillis(millis/10))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath(xPath)));
        } catch (TimeoutException e) {
            Utils.logger.error("TimeoutException");
        }
    }

    public static void closeFirstPopUp() {
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_FIRST_POP_UP_BUTTON)))
                    .click();
        } catch (TimeoutException e) {
        }
    }
    public static void closeSecondPopUp() {
        try {
            Utils.logger.info("Close Second PopUp window");
            getDriver().switchTo().defaultContent();
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(CATECORY_SECOND_POP_UP_CONTINUE_BUTTON)))
                    .click();
        } catch (TimeoutException e) {
        }
    }

    public static void closeSecond2PopUp() {
        try {
            try {
                Utils.logger.info("Close Second PopUp window");
                new WebDriverWait(Driver.getDriver().switchTo().defaultContent(), Duration.ofSeconds(1))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(HOME_SECOND_POP_UP_CLOSE_BUTTON)))
                        .click();
            } catch (TimeoutException e) {
                Utils.logger.error("Second PopUp window TimeoutException");
            }
        } catch (NoSuchElementException e) {
            Utils.logger.error("Second PopUp window NoSuchElementException");
        }
    }

    public static void acceptCookie() {
        try {
            Driver.getDriver().findElement(By.id(HOME_ACCEPT_COOKIES_BUTTON_ID)).click();
        } catch (NoSuchElementException e) {

        }
    }

    public static List<WebElement> getWebElementListByXpath(String locator) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
    }

    public static @Nullable WebElement getWebElementListItemByName(String locator, String itemName) {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(locator);
        for (WebElement menuItem : webElementList) {
            if (menuItem.getText().contains(itemName)) {
                return menuItem;
            }
        }
        Utils.logger.error("Element \"" + itemName + "\' not found");
        return null;
    }
}
