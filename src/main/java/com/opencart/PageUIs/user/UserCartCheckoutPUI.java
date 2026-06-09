package com.opencart.PageUIs.user;

public class UserCartCheckoutPUI {
    public static final String TEXTBOX_BY_NAME = "CSS=input[name='%s']";
    public static final String NEW_ADDRESS_RADIO = "xpath=//label[text()='I want to use a new address']";
    public static final String COUNTINUE_BUTTON = "id=button-shipping-address";
    public static final String COUNTRY_DROPDOWN = "NAME=country_id";
    public static final String REGION_DROPDOWN = "NAME=zone_id";

    public static final String CHOOSE_SHIPPING_METHOD_BUTTON = "ID=button-shipping-methods";
    public static final String CHOOSE_PAYMENT_METHOD_BUTTON = "ID=button-payment-methods";
    public static final String SHIPPING_METHOD_TEXT = "css=div.input-group input[name='shipping_method']";
    public static final String PAYMENT_METHOD_TEXT = "css=div.input-group input[name='payment_method']";

    public static final String SHIPPING_RATE_TEXT_IN_SHIPPING_METHOD_POPUP = "css=form#form-shipping-method label";
    public static final String OPTION_IN_PAYMENT_METHOD_POPUP = "css=form#form-payment-method label";
    public static final String COUNTINUE_BUTTON_IN_SHIPPING_METHOD_POPUP = "id=button-shipping-method";
    public static final String COUNTINUE_BUTTON_IN_PAYMENT_METHOD_POPUP = "id=button-payment-method";

    public static final String PLAT_SHIPPING_RATE = "XPATH=//div[@id='checkout-confirm']//td[contains(.,'Flat Shipping Rate')]/following-sibling::td";
    public static final String CONFIRM_ORDER_BUTTON = "xpath=//button[@id='button-confirm']";
    public static final String ORDER_SUCCESS_CONTENT_TEXT = "css=div#content>h1";

}
