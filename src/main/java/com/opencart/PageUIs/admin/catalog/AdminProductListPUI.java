package com.opencart.PageUIs.admin.catalog;

public class AdminProductListPUI {
    public static final String FILTER_BUTTON = "xpath=//button[@id='button-filter']";
    public static final String RESET_SEARCH_BUTTON = "CSS=button[type='reset']";

    public static final String PRODUCT_NAME_TEXTBOX = "id=input-name";
    public static final String MODEL_TEXTBOX = "id=input-model";
    public static final String CATEGORIES_PARENT = "ID=input-category";
    public static final String CATEGORIES_CHILD = "css=ul#autocomplete-category a.dropdown-item";
    public static final String MANUFACTURER_PARENT = "ID=input-manufacturer";
    public static final String MANUFACTURER_CHILD = "css=ul#autocomplete-manufacturer a.dropdown-item";
    public static final String PRICE_FROM_TEXTBOX = "id=input-price-from";
    public static final String PRICE_TO_TEXTBOX = "id=input-price-to";
    public static final String QUANTITY_FROM_TEXTBOX = "id=input-quantity-from";
    public static final String STATUS_DROPDOWN = "ID=input-status";

    public static final String DYNAMIC_PRODUCT_NAME_SEARCH_RESULT = "xpath=//div[@class='table-responsive']//td[contains(.,'%s')]";
    public static final String PRODUCT_NAME_SEARCH_BY_INDEX = "xpath=//div[@class='table-responsive']//td[3]";
    public static final String PRICE_SEARCH_RESULT = "xpath=//div[@class='table-responsive']//td[5]";
    public static final String QUANTITY_SEARCH_RESULT = "xpath=//div[@class='table-responsive']//td[6]/span";
    public static final String NO_RESULT_TEXT = "css=td.text-center";

    public static final String CHECKBOX_AT_TABLEHEADER = "XPATH=//th[text()='Image']/preceding::th/input[@type='checkbox']";
    public static final String ALL_ITEM_CHECKBOX = "CSS=input[type='checkbox']";
    public static final String DYNAMIC_ONE_ITEM_CHECKBOX = "xpath=//td[text()='%s']/preceding-sibling::td/input[@type='checkbox']";

    public static final String CLONE_BUTTON = "css=i.fa-copy";
    public static final String DELETE_BUTTON = "css=i.fa-trash-can";
    public static final String SUCCESS_ALERT = "CSS=div.alert-success";
    public static final String ADD_NEW_ITEM_BUTTON = "CSS=a.btn-primary>i.fa-plus";
    public static final String DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME = "xpath=//td[text()='%s']/following-sibling::td//i[@class='fa-solid fa-pencil']";


}
