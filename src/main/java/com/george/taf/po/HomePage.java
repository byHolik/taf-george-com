package com.george.taf.po;

import com.george.taf.utils.Driver;
import com.george.taf.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.george.taf.data.UiLocators.*;

public class HomePage {

    public HomePage() {
        LoginPage.logger.info("Open Home page");
        Driver.getDriver().get("https://george.com/");
        Driver.closeFirstPopUp();
        Driver.acceptCookie();

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public String getCopyrightText() {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_COPYRIGHT)))
                .getText();
    }

    public List<String> getCategoryMenuItemTexts() {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(HOME_MENU_LIST);
        List<String> actualCategoryMenuItemTexts = new ArrayList<>();
        for (WebElement menuItem : webElementList) {
            actualCategoryMenuItemTexts.add(menuItem.getText());
        }
        return actualCategoryMenuItemTexts;
    }

    public void clickCategoryItem(String menuItemName) {

        Utils.logger.info("Click to Category Item");
        Driver.getWebElementListItemByName(HOME_MENU_LIST, menuItemName).click();
/*
        List<WebElement> webElementList = Driver.getWebElementListByXpath(HOME_MENU_LIST);
        for (WebElement menuItem : webElementList) {
            if (menuItem.getText().equals(menuItemName)) {
                menuItem.click();
            }*/
        }
        public HomePage sendSearchinput(String productName){
            Utils.logger.info("Send Search input");
            WebElement searchTextBox = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(CATEGORY_SEARCH_INPUT_ID)));
            searchTextBox.sendKeys(productName);
            searchTextBox.sendKeys(Keys.ENTER);
            return this;
        }
    public void openBasket() {
        //Driver.pauseFluent(BASKET_MINI_CHECKOUT_BUTTON, 2000);
        Utils.logger.info("Open basket");
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BASKET_LINK))).click();
        } catch (TimeoutException e) {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BASKET_MENU_LINK))).click();
        }
    }

    public void clickAccountLink() {
        Driver.getDriver().findElement(By.xpath(HOME_ACCOUNT_LINK)).click();
    }
}
