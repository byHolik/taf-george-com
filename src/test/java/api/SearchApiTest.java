package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class SearchApiTest {
    @Test
    public void test1() {
        String searchProductName = "Navy Smart Slip On Pumps";
        String searchProductId = "GEM1006959";

        RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .param("q", searchProductName)
                .param("searchDEP", "true")
                .when().get("https://global.direct.asda.com/george/clothing/10,default,sc.html")
                .then()
                .assertThat()
                .body("productSearch.searchKeywords", equalTo(searchProductName))
                .body("productSearch.totalCount", equalTo(1))
                .body("productSearch.products[0].id", equalTo(searchProductId));
    }
}
