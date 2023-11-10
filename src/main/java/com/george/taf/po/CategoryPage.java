package com.george.taf.po;

import com.george.taf.data.ExpectedResults;
import com.george.taf.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CategoryPage {
    private String categoryNameLocator = "//*[@class='category-list']/h3";
    private String subCategoryItemsLocator = "//a[@class='text-link text-underline-hover']";
    private String sortingDropeDownLocator = "//button[@data-id='sorting_dropdown']/div";
    private String sortingItemLocator = "//button[@data-type='sorting_items']";
    private String produCtcontainerLocator = "//div[@class='mini-container']";

    public CategoryPage(String menuItemName) {
        new HomePage().clickToCategory(menuItemName);
    }

    public String getActualCategoryName() {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(categoryNameLocator))).getText();
    }

    public List<String> getActualSubCategoryMenuItemTexts() {
        List<WebElement> webElementList = Driver.getWebElementListByXpath(subCategoryItemsLocator);
        List<String> actualCategoryMenuItemTexts = new ArrayList<>();
        for (WebElement menuItem : webElementList) {
            actualCategoryMenuItemTexts.add(menuItem.getText());
        }
        return actualCategoryMenuItemTexts;
    }

    public List<String> getExpectedSubCategoryMenuItemTexts(String name) {
/*        for (WebElement element: Driver.getWebElementListByXpath(subCategoryubItemsLocator)){
            if (element.getText().equals(name)){
                return ExpectedResults.EXPECTED_MEN_CATEGORY_MENU_ITEM_TEXTS;
            }
        }*/
        switch (name) {
            case "Men":
                return ExpectedResults.MEN_CATEGORY_MENU_ITEM_TEXTS;
            default:
                return null;
        }
    }

    public CategoryPage clickToSubCategory(String menuItemName) {
        Driver.clickToListItemByName(subCategoryItemsLocator, menuItemName);
        return this;
    }
    public CategoryPage clickToSortButton() {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Driver.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(sortingDropeDownLocator)))
                .click();
        return this;
    }
    public CategoryPage clickToSortItem(String itemName) {
        Driver.clickToListItemByName(sortingItemLocator, itemName);
        return this;
    }

    public CategoryPage clickToFirstProductListItem(int index) {
        Driver.getWebElementListByXpath(produCtcontainerLocator).get(index).click();
        return this;
    }
}
