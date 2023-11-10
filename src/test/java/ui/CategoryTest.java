package ui;

import com.george.taf.po.CategoryPage;

import com.george.taf.po.CategoryPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryTest extends BaseTest {
    @Test
    @DisplayName("Open Category Page")
    public void openCategoryTest() {
        String categoryName = "Men";

        CategoryPage categoryPage = new CategoryPage(categoryName);
        Assertions.assertEquals(
                categoryName,
                categoryPage.getActualCategoryName());
    }

    @Test
    public void categoryTest() {
        String categoryName = "Men";

        CategoryPage categoryPage = new CategoryPage(categoryName);
        Assertions.assertEquals(
                categoryPage.getExpectedSubCategoryMenuItemTexts(categoryName),
                categoryPage.getActualSubCategoryMenuItemTexts());
    }

    @Test
    public void categoryTest2() {
        String categoryName = "Men";
        String subCategoryName = "Boots & Wellies";

        new CategoryPage(categoryName)
                .clickToSubCategory(subCategoryName)
                .clickToSortButton()
                .clickToSortItem("Price [Low - High]")
                .clickToFirstProductListItem(0);
    }

}
