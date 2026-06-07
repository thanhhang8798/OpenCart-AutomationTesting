package com.opencart.PageUIs.user;

public class UserShoppingCartPUI {
    public static final String COLUMNS_BEFORE_PRODUCT_COLUMN = "xpath=//th[text()='Product']/preceding-sibling::th";
    public static final String COLUMNS_BEFORE_TOTAL_COLUMN = "xpath=//th[text()='Total']/preceding-sibling::th";
    public static final String LIST_PRODUCT_NAME_COLUMN = "xpath=//div[@id='output-cart']//tbody//td[%s]/a";
    public static final String LIST_PRICE_COLUMN = "xpath=//div[@id='output-cart']//tbody//td[%s]";

    public static final String TOTAL_PRICE_TEXT = "xpath=//tfoot[@id='checkout-total']//strong[text()='Total']/ancestor::td/following-sibling::td";


}
