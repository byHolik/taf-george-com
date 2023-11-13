package ui;

import com.george.taf.data.UiExpectedResults;
import com.george.taf.data.TestData;
import com.george.taf.po.BasketPage;
import com.george.taf.po.CategoryPage;
import com.george.taf.po.HomePage;
import com.george.taf.po.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BasketTest extends BaseTest {

    @Test
    @DisplayName("Basket Open Test")
    public void basketTest() {
        logger.info("Start Basket Test - 'Basket Open Test'");
        new HomePage()
                .openBasket();

        Assertions.assertEquals(
                UiExpectedResults.EMPTY_BASKET_MESSAGE,
                new BasketPage().getEmptyBasketText());
    }

    @Test
    @DisplayName("Basket Remove All Items Test")
    public void basketRemoveAllTest() {
        logger.info("Start Basket Test - 'Basket Remove All Items Test'");

        new HomePage()
                .sendSearchinput(TestData.PRODUCT_NAME);
        new CategoryPage()
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();
        new CategoryPage()
                .sendSearchinput(TestData.SECOND_PRODUCT_SIZE_ID)
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();
        new BasketPage().removeAllProducts();

        if (!TestData.testResult) {
            Assertions.fail(TestData.testResultMessage);
            TestData.testResult = true;
            TestData.testResultMessage = null;
        }
        Assertions.assertEquals(
                UiExpectedResults.EMPTY_BASKET_MESSAGE,
                new BasketPage().getEmptyBasketText());
    }

    @Test
    @DisplayName("Basket Remove One Of several Item Test")
    public void basketRemoveSelectedProductTest() {
        logger.info("Start Basket Test - 'Basket Remove One Of several Item Test'");

        new HomePage()
                .sendSearchinput(TestData.PRODUCT_NAME);
        new CategoryPage()
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();
        new CategoryPage()
                .sendSearchinput(TestData.SECOND_PRODUCT_SIZE_ID)
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();
        BasketPage basket = new BasketPage();
        Assertions.assertEquals(
                TestData.PRODUCT_NAME,
                new BasketPage().getProductName(TestData.PRODUCT_NAME));
        basket.clickRemoveButton(TestData.PRODUCT_NAME)
                .clickConfirmRemoveButton(TestData.PRODUCT_NAME);

        Assertions.assertFalse(
                TestData.PRODUCT_NAME
                        .equals(basket.getProductName(TestData.PRODUCT_NAME)
                ));
    }

    @Test
    @DisplayName("Basket Remove The Only Item Test")
    public void basketRemoveTest() {
        logger.info("Start Basket Test - 'Basket Remove The Only Item Test'");

        new HomePage()
                .sendSearchinput(TestData.PRODUCT_NAME);
        new CategoryPage()
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();
        new BasketPage()
                .clickRemoveButton(TestData.PRODUCT_NAME)
                .clickConfirmRemoveButton(TestData.PRODUCT_NAME);

        Assertions.assertEquals(
                UiExpectedResults.EMPTY_BASKET_MESSAGE,
                new BasketPage().getEmptyBasketText());
    }

    @Test
    @DisplayName("Basket All Products Quantity Test")
    public void basketProductsQuantityTest() {
        logger.info("Start Basket Test - 'Basket All Products Quantity Test'");

        new HomePage()
                .sendSearchinput(TestData.PRODUCT_NAME);
        new CategoryPage()
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();

        new CategoryPage()
                .sendSearchinput(TestData.SECOND_PRODUCT_SIZE_ID)
                .clickToProductListItemNumber(0);
        new ProductPage()
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddToBasketButton()
                .openBasket();

        Assertions.assertEquals(
                2,
                new BasketPage().getProductQuantity());
    }

    @Test
    @DisplayName("Basket Full Test For One Product")
    public void AddToBasketFullTest() {
        logger.info("Start Basket Test - 'Basket Full Test'");
        int quantity = 3;
        String productName;
        List<Integer> productPrices;


        new CategoryPage(TestData.CATEGORY_NAME)
                .clickSubCategory(TestData.SUBCATEGORY_NAME)
                .clickSortButton()
                .clickSortListItem(TestData.SORT_LIST_ITEM_NAME)
                .clickToProductListItemNumber(1);
        ProductPage productPage = new ProductPage();
        productName = productPage.getProductName();
        productPrices = productPage.getProductPrices(quantity);
        productPage
                .clickToSizeItem(TestData.PRODUCT_SIZE_NAME)
                .clickAddQuantityButton(quantity)
                .clickAddToBasketButton();
        BasketPage basketPage = new BasketPage();
        basketPage.openBasket();

        if (!TestData.testResult) {
            Assertions.fail(TestData.testResultMessage);
            TestData.testResult = true;
            TestData.testResultMessage = null;
        }
        Assertions.assertEquals(UiExpectedResults.FULL_BASKET_TEXT,
                new BasketPage().getFullBasketText());
        Assertions.assertEquals(
                productName,
                new BasketPage().getProductName(productName));
        Assertions.assertEquals(
                String.valueOf(quantity),
                new BasketPage().getProductQuantity(productName));
        Assertions.assertEquals(
                productPrices,
                new BasketPage().getProductPrices(productName));
    }
}
