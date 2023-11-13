package com.george.taf.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.george.taf.ro.Error;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
    public static final Logger logger = LogManager.getLogger();
    public static String getLoginResponseString(Response response) {
        return response.then().extract().asString();
    }

    public static Error getLogInErrorObj(String responseString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        String str = "\"{\"status\":\"ERROR\",\"errorCode\":\"INVALID_CREDENTIALS\",\"errorDescription\":\"We do not recognise that email address or password. Please check and try again.\",\"challenge\":0}\"";
        return mapper.readValue(str, Error.class);
    }

    public static Error getErrorObject(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        return mapper.readValue(json, Error.class);
    }

    public static void getErrorVal(String json) throws JsonProcessingException {
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        String tweet = mapper.readValue(json, String.class);
        JsonNode root = mapper.readTree(json);
        String str = root.path("error.errorCode").asText();
        System.out.println(str);
    }

    public static String randomEmailGenerator(int emailLength) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        String domain = "test.com";

        StringBuilder email = new StringBuilder();

        for (int i = 0; i < emailLength; i++) {
            int charIndex = (int) (Math.random() * characters.length());
            email.append(characters.charAt(charIndex));
        }

        email.append('@').append(domain);
        return email.toString();
    }

    public static String randomPasswordGenerator(int emailLength) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_+=<>?";
        StringBuilder email = new StringBuilder();

        for (int i = 0; i < emailLength; i++) {
            int charIndex = (int) (Math.random() * characters.length());
            email.append(characters.charAt(charIndex));
        }

        return email.toString();
    }

    public static int getIntegerPrice(String price) {
        return Integer.parseInt(price.replace("€", ""));
    }

}
