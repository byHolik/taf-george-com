package com.george.taf.data;

public class UiLocators {

    public static final String HOME_FIRST_POP_UP_BUTTON = "//*[@type='button' and @data-key='continueShop']";
    public static final String HOME_ACCEPT_COOKIES_BUTTON_ID = "onetrust-accept-btn-handler";
    public static final String HOME_SECOND_POP_UP_CLOSE_BUTTON = "//*[@class='glClose']";
    public static final String CATECORY_SECOND_POP_UP_CONTINUE_BUTTON = "//*[@data-key='continueShop']";
    public static final String HOME_COPYRIGHT = "//*[@id='footer-region']/div[3]/div[3]/div/span";
    public static final String HOME_ACCOUNT_LINK = "//*[@data-id='link-header-my-account']";
    public static final String HOME_MENU_LIST = "//*[contains(@class, 'navigation__item')]";

    public static final String LOG_IN_HELP_LINK = "//*[@data-id='help-link-header']";
    public static final String LOG_IN_HOME_SITE_LINK = "//div[@class='asda_sites']/ul/li[2]/a";
    public static final String LOG_IN_SIGN_IN_BTN = "//*[@class='sign-in_btn']";
    public static final String LOG_IN_USER_NAME_INPUT = "//*[contains(@class, 'email-phone-input')]";
    public static final String LOG_IN_PASSWORD_ID = "password";
    public static final String LOG_IN_ALERT = "//*[@role='alert']";
    public static final String LOG_IN_SUBMIT = "//button[@type='submit']";
    public static final String LOG_IN_PAGE_NAME = "//div[@class='login-container']/h1";

    public static final String CATEGORY_NAME = "//*[@class='category-list']/h3";
    public static final String CATEGORY_SUB_MENU = "//a[@class='text-link text-underline-hover']";
    public static final String CATEGORY_DROP_DOWN_BUTTON = "//button[@data-id='sorting_dropdown']/div";
    public static final String CATEGORY_DROP_DOWN_LIST = "//button[@data-type='sorting_items']";
    public static final String CATEGORY_PRODUCT_LIST = "//div[@class='mini-container']";
    public static final String CATEGORY_PRODUCT_NAME_LIST = "//a[@class='text-underline-hover title']";
    public static final String CATEGORY_QUICK_BUY_BUTTON = "//div[@class='add-to-bag-plp']/div/div";
    public static final String CATEGORY_SEARCH_INPUT_ID = "search";
    public static final String CATEGORY_SEARCH_BUTTON = "//span[@data-id='button-header-search']";
    public static final String CATEGORY_EMPTY_RESULT_MESSAGE_ID = "intro-copy";

    public static final String PRODUCT_SIZE_LIST = "//span[@data-id='attribute-selector-value']";
    public static final String PRODUCT_ADD_TO_BASKET_BUTTON = "//button[@data-id='button-addtobag-pdp']";
    public static final String PRODUCT_ADD_QUANTITY_BUTTON = "//div[@data-id='button-increment-quantity-pdp']";
    public static final String PRODUCT_NAME = "//*[@class='product__title']";
    public static final String PRODUCT_PRICE = "//div[@class='price-badge']";

    public static final String BASKET_MENU_LINK = "//a[@data-id='link-header-basket']/span";
    public static final String BASKET_LINK = "//a[@data-id='link-mini-basket-summary-edit-bag']";
    public static final String BASKET_EMPTY_BASKET_TEXT = "//div[@data-id='empty-basket']/h2";
    public static final String BASKET_FULL_BASKET_TEXT = "//div[@data-id='basket-container']//h2/div";
    public static final String BASKET_PRODUCT_LIST = "//div[@class='basket-item-container']";
    public static final String BASKET_PRODUCT_ITEM_NAME = "//div[@class='basket-item-name']/a";
    public static final String BASKET_PRODUCT_ITEM_PRICES = "//div[@class='basket-content-item']//span[@class='price']";
    public static final String BASKET_PRODUCTS_PRICE = "//div[@class='basket-summary-subtotal']/div";
    public static final String BASKET_PRODUCT_ITEM_QUANTITY = "//div[@class='quantity-selector__field']/input";
    public static final String BASKET_CHECKOUT_BUTTON = "//button[@data-id='button-cart-to-checkout-mobile']";
    public static final String BASKET_MINI_CHECKOUT_BUTTON = "//button[@data-id='button-mini-basket-to-checkout']";
    public static final String BASKET_REMOVE_ITEM_BUTTON = "//span[contains(@data-id,'button-basket-remove-item')]";
    public static final String BASKET_CONFIRM_REMOVED_BUTTON = "//*[@name='item-removed']";
    public static final String BASKET_KEEP_SHOPPING_BUTTON = "//a[@data-id='button-basket-keep-shopping']";
}
