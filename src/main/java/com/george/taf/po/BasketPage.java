package com.george.taf.po;

import com.george.taf.utils.Driver;
import com.george.taf.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.george.taf.data.UiLocators.*;

public class BasketPage {
    public BasketPage() {
        Driver.pauseFluent(BASKET_CHECKOUT_BUTTON, 5000);
    }

    public BasketPage openBasket() {
        //Driver.pauseFluent(BASKET_MINI_CHECKOUT_BUTTON, 2000);
        Utils.logger.info("Open basket");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BASKET_LINK))).click();
        return this;
    }

    private WebElement getProductByName(String productName) {
        //Driver.pauseFluent(BASKET_CHECKOUT_BUTTON,5000);
        List<WebElement> productList = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BASKET_PRODUCT_LIST)));
        for (WebElement product : Driver.getWebElementListByXpath(BASKET_PRODUCT_LIST)) {
            if (product.findElement(By.xpath(BASKET_PRODUCT_ITEM_NAME)).getText().equals(productName)) {
                return product;
            }
        }
        return null;
    }
    public int getProductQuantity() {
       return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BASKET_PRODUCT_LIST))).size();
    }


    public List<Integer> getProductPrices(String productName) {
        Utils.logger.info("Get product prices");
        List<Integer> priceList = new ArrayList<>();
        List<WebElement> elementLIST = getProductByName(productName).findElements(By.xpath(BASKET_PRODUCT_ITEM_PRICES));
        for (WebElement price : elementLIST) {
            priceList.add(Utils.getIntegerPrice(price.getText()));
        }
        return priceList;
    }

    public int getProductSinglePrice(String productName) {
        return getProductPrices(productName).get(0);
    }

    public int getProductFullPrice(String productName) {
        if (getProductPrices(productName).size() > 0) {
            return getProductPrices(productName).get(1);
        }
        return 0;
    }

    public String getProductQuantity(String productName) {
        Utils.logger.info("Get product quantity");
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOf(getProductByName(productName).findElement(By.xpath(BASKET_PRODUCT_ITEM_QUANTITY))))
                .getAttribute("value");
    }

    public String getProductName(String productName) {
        Utils.logger.info("Get product name");
        try {
            return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.visibilityOf(getProductByName(productName).findElement(By.xpath(BASKET_PRODUCT_ITEM_NAME)))).
                    getText();
        }catch (NullPointerException e) {
            return null;
        }
    }

    public BasketPage clickRemoveButton(String productName) {
        Utils.logger.info("Click remove button");
        Driver.pauseFluent(BASKET_CHECKOUT_BUTTON, 3000);
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(getProductByName(productName).findElement(By.xpath(BASKET_REMOVE_ITEM_BUTTON)))).click();
        return this;
    }

    public BasketPage clickConfirmRemoveButton(String productName) {
        Utils.logger.info("Click Confirm removed");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BASKET_CONFIRM_REMOVED_BUTTON))).click();
        return this;
    }

    public BasketPage removeAllProducts() {
        Utils.logger.info("Remove all products");
        List<WebElement> productList = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BASKET_PRODUCT_LIST)));
        /*        int i = productList.size();*/
        for (int i = productList.size(); i > 0; i--) {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(productList.get(i - 1).findElement(By.xpath(BASKET_REMOVE_ITEM_BUTTON)))).click();
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(BASKET_CONFIRM_REMOVED_BUTTON))).click();

            if (i > 1) {
                Driver.pause(3000);
                productList = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BASKET_PRODUCT_LIST)));
            }
        }
/*        do {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(productList.get(i).findElement(By.xpath(BASKET_REMOVE_ITEM_BUTTON)))).click();
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(BASKET_CONFIRM_REMOVED_BUTTON))).click();

            if (i > 1) {
                productList = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BASKET_PRODUCT_LIST)));
            }
            i--;
        } while (i > 0);*/
        return this;
    }

    public String getEmptyBasketText() {
        Utils.logger.info("Get empty basket text");
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BASKET_EMPTY_BASKET_TEXT))).getText();

    }

    public String getFullBasketText() {
        Utils.logger.info("Get ful basket text");
        Driver.pauseFluent(BASKET_CHECKOUT_BUTTON, 5000);
        return Driver.getDriver().findElement(By.xpath(BASKET_FULL_BASKET_TEXT)).getText();
    }
}
