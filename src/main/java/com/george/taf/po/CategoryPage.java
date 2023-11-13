package com.george.taf.po;

import com.george.taf.utils.Driver;
import com.george.taf.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.george.taf.data.UiLocators.*;

public class CategoryPage {

    public CategoryPage(){
    };

    public CategoryPage(String category) {
        openCategory(category);
    }
    public void openCategory(String category) {
        Utils.logger.info("Open Category page");
        new HomePage().clickCategoryItem(category);
        try {
            Driver.pauseFluent(CATEGORY_SUB_MENU, 10000);
        }catch (TimeoutException e) {
            Utils.logger.error("CategoryPage open TimeoutException");
        }
    }

    public String getEmptyResultsText() {
        Utils.logger.info("Get empty result message");
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(CATEGORY_EMPTY_RESULT_MESSAGE_ID)))
                .getText();
    }

    public String getCategoryName() {
        Utils.logger.info("Get Category Name");
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(CATEGORY_NAME))).getText();
    }

    public List<String> getSubCategoryMenuItemTexts() {
        Utils.logger.info("Get Actual SubCategory MenuItem Texts");
        List<WebElement> webElementList = Driver.getWebElementListByXpath(CATEGORY_SUB_MENU);
        List<String> actualCategoryMenuItemTexts = new ArrayList<>();
        for (WebElement menuItem : webElementList) {
            actualCategoryMenuItemTexts.add(menuItem.getText());
        }
        return actualCategoryMenuItemTexts;
    }

    public CategoryPage clickSubCategory(String menuItemName) {
        Utils.logger.info("Click to SubCategory Item");
        //Driver.pauseFluent(CATEGORY_SUB_MENU, 5000);
        Driver.getWebElementListItemByName(CATEGORY_SUB_MENU, menuItemName).click();
        Driver.closeSecondPopUp();
        return this;
    }

    public CategoryPage clickSortButton() {
        Utils.logger.info("Click Sort Button");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(CATEGORY_DROP_DOWN_BUTTON)))
                .click();
        return this;
    }

    public CategoryPage clickSortListItem(String itemName) {
        Utils.logger.info("Click SortList Item");
        Driver.getWebElementListItemByName(CATEGORY_DROP_DOWN_LIST, itemName).click();
        return this;
    }

    public CategoryPage clickToProductListItemNumber(int index) {
        Utils.logger.info("Click First ProductListItem");
        Driver.pauseFluent(CATEGORY_QUICK_BUY_BUTTON,5000);
        Driver.getWebElementListByXpath(CATEGORY_PRODUCT_LIST).get(index).click();
        return this;
      }
    public List<String> getProductNameList() {
        Utils.logger.info("Get Product Name List");
        List<String> productNameList = new ArrayList<>();
        Driver.pauseFluent(CATEGORY_QUICK_BUY_BUTTON,5000);
        for (WebElement product:Driver.getWebElementListByXpath(CATEGORY_PRODUCT_NAME_LIST)) {
            productNameList.add(product.getText());
        }
        return productNameList;
    }
    public CategoryPage sendSearchinput(String productName){
        Utils.logger.info("Send Search input");
        WebElement searchTextBox = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(CATEGORY_SEARCH_INPUT_ID)));
        searchTextBox.sendKeys(productName);
        searchTextBox.sendKeys(Keys.ENTER);
        return this;
    }
}
