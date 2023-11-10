package com.george.taf.po;

import com.george.taf.data.ExpectedResults;
import com.george.taf.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private final String expectedCopyrightText = "© George 2023";
    private String backToShopButtonLocator = "//*[@type='button' and @data-key='continueShop']";
    private String acceptCookiesButtonId = "onetrust-accept-btn-handler";
    private String copyrightLocator = "//*[@id='footer-region']/div[3]/div[3]/div/span";
    private String accountLinkLocator = "//*[@data-id='link-header-my-account']";
    private String categoryListLocator = "//*[contains(@class, 'navigation__item')]";



    public HomePage() {
        LoginPage.logger.info("Open Home page");
        Driver.getDriver().get("https://george.com/");

        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(backToShopButtonLocator)))
                    .click();
        } catch (TimeoutException e) {
        }
        try {
            Driver.getDriver().findElement(By.id(acceptCookiesButtonId)).click();
        } catch (NoSuchElementException e) {

        }

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Driver.pause();

/*        try {
            System.out.println("1");
            new FluentWait(Driver.getDriver())
                    .withTimeout(Duration.ofSeconds(1))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(backToShopButtonLocator)));
        } catch (TimeoutException e){
            System.out.println("2");
        }

        try {
            System.out.println("3");
            Driver.getDriver().findElement(By.id(acceptCookiesButtonId)).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("4");
            Driver.getDriver().findElement(By.xpath(backToShopButtonLocator)).click();
        } finally {
            System.out.println("5");
            Driver.getDriver().findElement(By.id(acceptCookiesButtonId)).click();
        }

        try {
            System.out.println("back to shop 1");
            Driver.getDriver().findElement(By.xpath(backToShopButtonLocator)).click();
        } catch (NoSuchElementException e) {
            System.out.println("cookie");
            Driver.getDriver().findElement(By.id(acceptCookiesButtonId)).click();
        } finally {
            System.out.println("back to shop 2");
        }


        try {
            System.out.println("back to shop 1");
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TestData.seconds))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(backToShopButtonLocator)))
                    .click();
        } catch (TimeoutException e) {
            System.out.println("cookie");
            Driver.getDriver().findElement(By.id(acceptCookiesButtonId)).click();
        } finally {
            System.out.println("back to shop 2");
        }

       new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TestData.seconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(acceptCookiesButtonId)))
                .click();*/
    }

    public String getActualCopyrightText() {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(copyrightLocator)))
                .getText();
    }

    public String getExpectedCopyrightText() {
        return expectedCopyrightText;
    }

    public List<String> getActualCategoryMenuItemTexts() {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(categoryListLocator);
        List<String> actualCategoryMenuItemTexts = new ArrayList<>();
        for(WebElement menuItem: webElementList) {
            actualCategoryMenuItemTexts.add(menuItem.getText());
        }
        return actualCategoryMenuItemTexts;
    }
    public List<String> getExpectedCategoryMenuItemTexts() {
        return ExpectedResults.CATEGORY_MENU_ITEM_TEXTS;
    }

    public void clickToCategory(String menuItemName) {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(categoryListLocator);
        for(WebElement menuItem: webElementList) {
            if (menuItem.getText().equals(menuItemName)) {
                menuItem.click();
            }
        }
    }

    public void clickAccountLink() {
        Driver.getDriver().findElement(By.xpath(accountLinkLocator)).click();
    }
}
