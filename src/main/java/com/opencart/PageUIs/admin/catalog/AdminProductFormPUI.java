package com.opencart.PageUIs.admin.catalog;

public class AdminProductFormPUI {
    public static final String PRODUCT_HEADER = "CLASS=card-header";

    public static final String DYNAMIC_TEXTBOX_BY_PLACEHOLDER = "css=input[placeholder='%s']";
    public static final String DYNAMIC_TAB_BY_TEXT = "xpath=//ul[@role='tablist']//a[text()='%s']";

    public static final String DESCRIPTION_IFRAME = "css=div.input-group iframe";
    public static final String DESCRIPTION_TEXTAREA = "css=body.cke_editable";

    public static final String CATEGORIES_PARENT = "ID=input-category";
    public static final String CATEGORIES_CHILD = "css=ul#autocomplete-category a.dropdown-item";
    public static final String MANUFACTURER_PARENT = "ID=input-manufacturer";
    public static final String MANUFACTURER_CHILD = "css=ul#autocomplete-manufacturer a.dropdown-item";
    public static final String MANUFACTURER_TEXT = "name=manufacturer";
    public static final String CATEGORY_TEXT = "xpath=//table[@id='product-category']//td[1]";

    public static final String SAVE_BUTTON = "xpath=//button[@title='Save']";

    public static final String SUCCESS_ALLERT = "CSS=div.alert-success";
}
