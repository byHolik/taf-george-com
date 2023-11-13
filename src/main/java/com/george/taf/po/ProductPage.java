package com.george.taf.po;

import com.george.taf.data.TestData;
import com.george.taf.utils.Driver;
import com.george.taf.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.george.taf.data.UiLocators.*;

public class ProductPage {
    public ProductPage() {
        Driver.pauseFluent(PRODUCT_SIZE_LIST, 5000);
    }

    public ProductPage(String productId) {
        Driver.getDriver().get("https://global.direct.asda.com/george/men/shoes/black-composite-safety-trainers/" + TestData.PRODUCT_ID + ",default,pd.html?cgid=D2M1G10C13");
        Driver.pauseFluent(PRODUCT_ADD_TO_BASKET_BUTTON, 5000);
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

    public String getProductName() {
        Utils.logger.info("Get product name");
                return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRODUCT_NAME)))
                .getText();

    }

    public List<Integer> getProductPrices(int quantity) {
        Utils.logger.info("Get product prices");
        List<Integer> priceList = new ArrayList<>();
        String stringPrice = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRODUCT_PRICE)))
                .getText();
        priceList.add(Utils.getIntegerPrice(stringPrice));
        if (quantity > 1) {
            priceList.add(Utils.getIntegerPrice(stringPrice) * quantity);
        }
        return priceList;
    }

    public ProductPage clickToSizeItem(String itemName) {
        Utils.logger.info("Click Size button");
        Driver.getWebElementListItemByName(PRODUCT_SIZE_LIST, itemName).click();
        return this;
    }

    public ProductPage clickAddToBasketButton() {
        Utils.logger.info("Click Add To Basket button");
        Driver.pauseFluent(BASKET_MINI_CHECKOUT_BUTTON, 5000);
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_ADD_TO_BASKET_BUTTON)))
                .click();
        return this;
    }

    public ProductPage clickAddQuantityButton(int quantity) {
        Driver.pauseFluent(PRODUCT_ADD_TO_BASKET_BUTTON, 5000);
        Utils.logger.info("Click Quantity button");
        if (--quantity> 0) {
            for (int i = 0; i <= quantity--; i++) {
                try {
                    new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRODUCT_ADD_QUANTITY_BUTTON)))
                            .click();
                }catch (TimeoutException e) {
                    String errorMessage = "clickAddQuantityButton TimeoutException: this size of product is now out of stock";
                    Utils.logger.error(errorMessage);
                    TestData.testResult = false;
                }
            }
        }
        return this;
    }
}
