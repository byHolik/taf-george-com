package ui;

import com.george.taf.data.UiExpectedResults;
import com.george.taf.data.TestData;
import com.george.taf.po.CategoryPage;
import com.george.taf.po.HomePage;
import com.george.taf.po.ProductPage;
import com.george.taf.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {

    @Test
    @DisplayName("Home Copyright Test")
    public void copyrightTest() {
        Utils.logger.info("Start Home Test - 'Home Copyright Test'");
        HomePage homePage = new HomePage();
        Assertions.assertEquals(
                UiExpectedResults.HOME_COPYRIGHT_TEXT,
                homePage.getCopyrightText());
    }

    @Test
    @DisplayName("Home Menu Test")
    public void categoryTest() {
        Utils.logger.info("Start Home Test - 'Home Menu Test'");
        HomePage homePage = new HomePage();
        Assertions.assertLinesMatch(
                UiExpectedResults.HOME_MENU_TEXTS,
                homePage.getCategoryMenuItemTexts());
    }

    @Test
    @DisplayName("Home Search Test")
    public void searchTest() {
        Utils.logger.info("Start Home Test - 'Home Search Test'");
        new HomePage()
                .sendSearchinput(TestData.PRODUCT_NAME);
        new CategoryPage()
                .clickToProductListItemNumber(0);

        Assertions.assertEquals(
                TestData.PRODUCT_NAME,
                new ProductPage().getProductName());
    }

    @Test
    @DisplayName("Home Empty Result Search Test")
    public void searchEmptyTest() {
        Utils.logger.info("Start Home Test - 'Home Empty Result Search Test'");
        new HomePage()
                .sendSearchinput(Utils.randomPasswordGenerator(10));

        Assertions.assertEquals(
                UiExpectedResults.CATEGORY_EMPTY_RESULT_MESSAGE,
                new CategoryPage().getEmptyResultsText());
    }
}
