package com.opencart.PageUIs.admin.sales;

public class AdminOrderDetailPUI {
    public static final String ORDER_STATUS_DROPDOWN = "ID=input-order-status";
    public static final String ADD_HISTORY_BUTTON = "ID=button-history";
    public static final String BACK_BUTTON = "xpath=//i[@class='fa-solid fa-reply']/ancestor::a";

    public static final String DYMAMIC_COLUMNS_BEFORE_BY_NAME = "xpath=//th[text()='%s']/preceding-sibling::th";
    public static final String DYNAMIC_COLUMN_OF_FIRST_ROW_IN_HISTORY_TABLE = "xpath=//div[@id='history']//tbody//tr[1]/td[%s]";
}
