package com.george.taf.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.george.taf.objects.BasketInfo;
import com.george.taf.objects.BasketItem;
import com.george.taf.objects.Error;
import com.george.taf.utils.Utils;
import io.restassured.response.Response;

public class ResponseObjects {
    public static Error expectedBasketNoQuantityError() {
        return new Error(
                "addToCartError",
                "noQuantityError",
                "Sorry, this product is now out of stock"
        );
    }

    public static Error expectedRemoveBasketError() {
        return new Error(
                "CannotRemoveProductError",
                "Unable to remove item from the cart. Please try again! If the issue continues please contact customer service."
        );
    }

    public static BasketItem expectedBasketItem(int quantity) {
        return new BasketItem(
                TestData.PRODUCT_SIZE_ID,
                quantity,
                TestData.PRODUCT_PRICE
        );
    }

    public static BasketInfo expectedEmptyBasket() {
        return new BasketInfo(
                0,
                0
        );
    }

    public static Error expectedSessionExpiredError() {
        return new Error(
                "CSRF",
                "Session expired",
                "Test_967b06617c10cec7be0a2"
        );
    }

    public static Error expectedAccountLockedLogIn() {
        return new Error(
                "ERROR",
                "ACCOUNT_LOCKED",
                "Sorry, for your security, " +
                        "your account has been locked. " +
                        "Please click 'forgotten password' to unlock your account.");
    }

    public static Error expectedInvalidUsernameLogin() {
        return new Error(
                "ERROR",
                "INVALID_USERNAME",
                "Please enter your email address");
    }

    public static Error expectedInvalidPasswordLogin() {
        return new Error(
                "ERROR",
                "INVALID_PASSWORD",
                "Please enter your password");
    }

    public static Error expectedInvalidCredentialLogin() {
        return new Error(
                "ERROR",
                "INVALID_CREDENTIALS",
                "We do not recognise that email address or password. Please check and try again.");
    }

    public static Error actualBasketNoQuantityError(String json) throws JsonProcessingException {

        return Utils.getErrorObject(json);
    }

    public static Error actualBasketNoQuantityError(Response response) {
        return new Error(
                response.then().extract().path("error.code").toString(),
                response.then().extract().path("error.errorCode").toString(),
                response.then().extract().path("error.message").toString()
        );
    }

    public static BasketItem actualBasketItem(Response response) {
        return new BasketItem(
                response.then().extract().path("result.basketInformation.items[0].id").toString(),
                Integer.valueOf(response.then().extract().path("result.basketInformation.items[0].quantity").toString()),
                Integer.valueOf(response.then().extract().path("result.basketInformation.items[0].priceValue").toString())
        );
    }

    public static BasketInfo actualEmptyBasketInformation(Response response) {
        return new BasketInfo(
                Integer.valueOf(response.then().extract().path("basketInformation.totalQuantity").toString()),
                Integer.valueOf(response.then().extract().path("basketInformation.totalCost").toString())
        );
    }

    public static Error actualRemoveBasketError(Response response) {
        return new Error(
                response.then().extract().path("error.code"),
                response.then().extract().path("error.message")
        );
    }

    public static Error actualSessionExpiredError(Response response) {
        return new Error(
                response.then().extract().path("error.errorCode"),
                response.then().extract().path("error.errorMessage"),
                response.then().extract().path("error.secretKey")
        );
    }

    public static Error actualLogInError(Response response) {
        return new Error(
                response.then().extract().path("status"),
                response.then().extract().path("errorCode"),
                response.then().extract().path("errorDescription")
        );
    }
}
