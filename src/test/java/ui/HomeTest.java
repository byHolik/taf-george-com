package ui;

import com.george.taf.po.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {

    @Test
    public void copyrightTest() {
        HomePage homePage = new HomePage();
        Assertions.assertEquals(
                homePage.getExpectedCopyrightText(),
                homePage.getActualCopyrightText());
    }

    @Test
    public void categoryTest() {
        HomePage homePage = new HomePage();
        Assertions.assertLinesMatch(
                homePage.getExpectedCategoryMenuItemTexts(),
                homePage.getActualCategoryMenuItemTexts());
    }
}
