package com.opencart.PageUIs.user;

public class UserProductDetailPUI {
    public static final String ADD_TO_CART_BUTTON = "ID=button-cart";
    public static final String ADD_TO_CART_INFOR_BUTTON = "css=div.dropdown.d-grid>button";
    public static final String LIST_PRODUCT_NAME_IN_CART_DROPDOWN = "XPATH=//div[@id='cart']//table[contains(@class,'table-striped')]//td[2]/a";
    public static final String TOTAL_PRICE_IN_CART_DROPDOWN = "XPATH=//strong[text()='Total']/ancestor::td/following-sibling::td";
    public static final String LIST_PRICE_IN_CART_DROPDOWN = "XPATH=//div[@id='cart']//table[contains(@class,'table-striped')]//td[4]";
    public static final String CHECKOUT_IN_CART_DROPDOWN = "xpath=//strong[text()=' Checkout']";
}
