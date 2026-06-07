package com.opencart.PageUIs.user;

public class UserProductPUI {
    public static final String ADD_TO_CART_INFOR_BUTTON = "css=div.dropdown.d-grid>button";
    public static final String DYNAMIC_ADD_TO_CART_BY_NAME = "xpath=//a[text()='%s']/ancestor::div[@class='content']//button[@aria-label='Add to Cart']";
    public static final String DYNAMIC_PRICE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::div[@class='content']//span[@class='price-new']";
    public static final String SUCCESS_ALERT = "css=div.alert-success";

    public static final String LIST_PRODUCT_NAME_IN_CART_DROPDOWN = "XPATH=//div[@id='cart']//table[contains(@class,'table-striped')]//td[2]/a";
    public static final String LIST_PRICE_IN_CART_DROPDOWN = "XPATH=//div[@id='cart']//table[contains(@class,'table-striped')]//td[4]";
    public static final String TOTAL_PRICE_IN_CART_DROPDOWN = "XPATH=//strong[text()='Total']/ancestor::td/following-sibling::td";
}
