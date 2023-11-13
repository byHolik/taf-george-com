package api;

import com.george.taf.data.TestData;
import com.george.taf.ro.Error;

import static com.george.taf.data.ApiResponseObjects.*;

import com.george.taf.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketApiTest {
    @DisplayName("Check response boby")
    public void toBasketBody() {
        Responses.getToBasketResponse(TestData.PRODUCT_SIZE_ID, 1)
                .then()
                .log()
                .all();
    }

    @Order(1)
    @Test
    @DisplayName("Check Session expired error")
    public void BasketSessionErrorTest() {
        Response response = Responses.getToBasketResponse(TestData.PRODUCT_SIZE_ID, 1);
        Error actualSessionExpiredError = actualSessionExpiredError(response);
        TestData.secretKey = actualSessionExpiredError.getDescription();

        System.out.println(TestData.secretKey);
        System.out.println(expectedSessionExpiredError());
        System.out.println(actualSessionExpiredError);

        Assertions.assertFalse(
                actualSessionExpiredError.equals(
                        expectedSessionExpiredError()
                )
        );
    }

    @Order(2)
    @Test
    @DisplayName("Check out of stock error")
    public void toBasketNoQuantityErrorTest() {
        int quantity = 100;

        Response response = Responses.getToBasketResponse(TestData.PRODUCT_SIZE_ID, quantity);
        response.then().statusCode(200);

        Assertions.assertEquals(
                expectedBasketNoQuantityError(),
                actualBasketNoQuantityError(response)
        );
    }

    @Order(3)
    @Test
    @DisplayName("Check deleting from basket error")
    public void outOfBasketErrorTest() {
        Response response = Responses.getOutOfBasketResponse();
        response.then().statusCode(200);

        Assertions.assertEquals(
                expectedRemoveBasketError(),
                actualRemoveBasketError(response));
    }

    @Order(4)
    @Test
    @DisplayName("Check deleting from basket")
    public void outOfBasketTest() {
        int quantity = 3;
        Response response = Responses.getToBasketResponse(TestData.PRODUCT_SIZE_ID, quantity);
        TestData.pliUUID = response.then().extract().path("result.pliUUID[0]");

        response = Responses.getOutOfBasketResponse();
        response.then().statusCode(200);

        Assertions.assertEquals(
                expectedEmptyBasket(),
                actualEmptyBasketInformation(response)
        );
    }

    @Order(5)
    @Test
    @DisplayName("Check selectet product")
    public void toBasketSelectTest() {
        int quantity = 3;
        Response response = Responses.getToBasketResponse(TestData.PRODUCT_SIZE_ID, quantity);
        response.then().statusCode(200);

        Assertions.assertEquals(
                expectedBasketItem(quantity),
                actualBasketItem(response)
        );
    }
}
