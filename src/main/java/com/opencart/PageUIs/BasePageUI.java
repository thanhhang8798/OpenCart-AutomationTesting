package com.opencart.PageUIs;

public class BasePageUI {
    public static final String UPLOAD_FILE_TYPE = "css=input[type='file']";
    public static final String CART_BUTTON = "css=div#cart button";
    public static final String VIEW_CART_BUTTON_IN_CART_DROPDOWN = "xpath=//strong[contains(.,'View Cart')]";
    public static final String CHECKOUT_BUTTON_IN_CART_DROPDOWN = "xpath=//strong[contains(.,'Checkout')]";
    public static final String CHECKOUT_BUTTON = "xpath=//span[text()='Checkout']";
    public static final String SUCCESS_MESSAGE_TEXT = "css=div.alert-success";
    public static final String PARENT_MY_ACCOUNT_DROPDOWN = "xpath=//span[text()='My Account']";
    public static final String CHILD_MY_ACCOUNT_DROPDOWN = "xpath=//span[text()='My Account']/ancestor::div[@class='dropdown']/ul//li/a";
}
