package com.george.taf.utils;

import com.george.taf.data.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Responses {

    public static Response getOutOfBasketResponse() {
        String URL = "https://global.direct.asda.com/on/demandware.store/Sites-ASDA-INT-Site/default/CartDEP-RemoveProductLineItem?uuid=" +
                TestData.pliUUID +
                "&secretKey=" +
                TestData.secretKey;

        return RestAssured
                .given()
                .body("{}")
                .header("authority", "global.direct.asda.com")
                .header("cookie", TestData.COOKIE)
                /*
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7")

                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.REFERER, "https://global.direct.asda.com/george/kids/new-in/D25M2G2C1,default,sc.html?cm_sp=e-desktop_p-homepage-global_t-tier05_b-block03_d-231024_l-link01_c-girlsnewin_s-AW_tp-moduleF")
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("Postman-Token", "fe4b13f2-6af3-4994-b3b5-c37dbf99e328")
                .header(HttpHeaders.HOST, "global.direct.asda.com")
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                */
                .when().post(URL);
    }

    public static Response getToBasketResponse(String productSizeId, int quantity) {
        String URL = "https://global.direct.asda.com/on/demandware.store/Sites-ASDA-INT-Site/default/CartDEP-AddProduct?secretKey=" + TestData.secretKey;
        String body = "{" +
                "\"pid\":\"" + productSizeId + "\"," +
                "\"quantity\":" + quantity +
                "}";

        return RestAssured
                .given()
                .body(body)
                .header("authority", "global.direct.asda.com")
                .header("cookie", TestData.COOKIE)
                /*
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7")

                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.REFERER, "https://global.direct.asda.com/george/kids/new-in/D25M2G2C1,default,sc.html?cm_sp=e-desktop_p-homepage-global_t-tier05_b-block03_d-231024_l-link01_c-girlsnewin_s-AW_tp-moduleF")
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .header("Postman-Token", "fe4b13f2-6af3-4994-b3b5-c37dbf99e328")
                .header(HttpHeaders.HOST, "global.direct.asda.com")
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                */
                .when().post(URL);
    }

    public static Response getLoginResponse(String username, String password) {
        String URL = "https://www.asda.com/api/v1/login?requestorigin=george";
        String body = "{" +
                "\"username\":\"" + username + "\"," +
                "\"redirect_uri\":\"https://global.direct.asda.com/george/clothing/10,default,sc.html\"," +
                "\"rememberme\":\"N\"," +
                "\"method\":\"authentication\"," +
                "\"password\":\"" + password + "\"" +
                "}";

        return RestAssured
                .given().body(body)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .when().post("https://www.asda.com/api/v1/login?requestorigin=george");

    }
}
