package com.george.taf.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public static void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WebElement> getWebElementListByXpath(String locator) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
    }
    public static void clickToListItemByName(String locator, String itemName) {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(locator);
        for(WebElement menuItem: webElementList) {
            if (menuItem.getText().contains(itemName)) {
                menuItem.click();
                return;
            }
        }
    }
}
