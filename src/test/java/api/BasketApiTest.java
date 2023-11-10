package api;

import com.george.taf.data.TestData;
import com.george.taf.ro.Error;

import static com.george.taf.data.ResponseObjects.*;

import com.george.taf.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketApiTest {
    @Order(1)
    @Test
    @DisplayName("Check Session expired error")
    public void BasketSessionErrorTest() {
        Response response = Responses.getToBasketResponse();
        Error actualSessionExpiredError = actualSessionExpiredError(response);
        TestData.secretKey = actualSessionExpiredError.getDescription();

        System.out.println(expectedSessionExpiredError());
        System.out.println(actualSessionExpiredError);
        System.out.println(TestData.secretKey);

        Assertions.assertFalse(
                actualSessionExpiredError.equals(
                        expectedSessionExpiredError()
                )
        );
    }

    @Disabled
    @Test
    @DisplayName("Check response boby")
    public void toBasketBodyTest() {
        Responses.getToBasketResponse()
                .then()
                .statusCode(200);
    }

    @Order(4)
    @Test
    @DisplayName("Check out of stock error")
    public void toBasketNoQuantityErrorTest() {
        int quantity = 100;

        Response response = Responses.getToBasketResponse(quantity);
        response.then().statusCode(200);

        Assertions.assertEquals(
                expectedBasketNoQuantityError(),
                actualBasketNoQuantityError(response)
        );
    }

    @Order(5)
    @Test
    @DisplayName("Check selectet product")
    public void toBasketSelectTest() {
        int quantity = 3;
        Response response = Responses.getToBasketResponse(quantity);
        response.then().statusCode(200);

        TestData.pliUUID = response.then().extract().path("result.pliUUID[0]");

        Assertions.assertEquals(
                expectedBasketItem(quantity),
                actualBasketItem(response)
        );
    }

    @Order(4)
    @Test
    @DisplayName("Check deleting from basket")
    public void outOfBasketTest() {
        Response response = Responses.getToBasketResponse();
        TestData.pliUUID = response.then().extract().path("result.pliUUID[0]");

        response = Responses.getOutOfBasketResponse();
        response.then().statusCode(200);

        //System.out.println(response.then().log().all());

        Assertions.assertEquals(
                expectedEmptyBasket(),
                actualEmptyBasketInformation(response)
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
}
