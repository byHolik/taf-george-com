package ui;

import com.george.taf.data.UiExpectedResults;
import com.george.taf.data.TestData;
import com.george.taf.po.CategoryPage;

import com.george.taf.po.ProductPage;
import com.george.taf.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryTest extends BaseTest {
    @Test
    @DisplayName("Open Category Page Test")
    public void openCategoryTest() {
        Utils.logger.info("Start Category Test - 'Open Category Page Test'");
        CategoryPage categoryPage = new CategoryPage(TestData.CATEGORY_NAME);
        Assertions.assertEquals(
                TestData.CATEGORY_NAME,
                categoryPage.getCategoryName());
    }

    @Test
    @DisplayName("Category Submenu Test")
    public void subCategoryTest() {
        Utils.logger.info("Start Category Test - 'Category Submenu Test'");
        CategoryPage categoryPage = new CategoryPage(TestData.CATEGORY_NAME);
        Assertions.assertEquals(
                UiExpectedResults.CATEGORY_MEN_MENU_TEXTS,
                categoryPage.getSubCategoryMenuItemTexts());
    }

    @Test
    @DisplayName("Category Search Test")
    public void searchTest() {
        Utils.logger.info("Start Category Test - 'Category Search Test'");
        new CategoryPage(TestData.CATEGORY_NAME)
                .sendSearchinput(TestData.PRODUCT_NAME)
                .clickToProductListItemNumber(0);

        Assertions.assertEquals(
                TestData.PRODUCT_NAME,
                new ProductPage().getProductName());
    }

    @Test
    @DisplayName("Category Empty Result Search Test")
    public void searchEmptyTest() {
        Utils.logger.info("Start Category Test - 'Category Empty Result Search Test'");
        new CategoryPage(TestData.CATEGORY_NAME)
                .sendSearchinput(Utils.randomPasswordGenerator(10));

        Assertions.assertEquals(
                UiExpectedResults.CATEGORY_EMPTY_RESULT_MESSAGE,
                new CategoryPage().getEmptyResultsText());
    }

    @Test
    @DisplayName("Category Product Test")
    public void categoryProductsTest() {
        Utils.logger.info("Start Category Test - 'Basket Full Test'");

        CategoryPage categoryPage = new CategoryPage(TestData.CATEGORY_NAME)
                .clickSubCategory(TestData.SUBCATEGORY_NAME);

        for (String productName : categoryPage.getProductNameList()) {
            Assertions.assertFalse(
                    productName.equals(UiExpectedResults.CATEGORY_PRODUCT_NAME_1) ||
                            productName.equals(UiExpectedResults.CATEGORY_PRODUCT_NAME_2), productName);
        }
    }
}
