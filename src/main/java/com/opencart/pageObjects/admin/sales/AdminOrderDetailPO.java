package com.opencart.pageObjects.admin.sales;

import com.opencart.PageUIs.admin.sales.AdminOrderDetailPUI;
import com.opencart.PageUIs.admin.sales.AdminOrdersPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;

public class AdminOrderDetailPO extends BasePage {
    private WebDriver driver;

    public AdminOrderDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemInOrderStatusDropdown(String status) {
        waitElementVisible(driver, AdminOrderDetailPUI.ORDER_STATUS_DROPDOWN);
        selectItemInDropdown(driver, AdminOrderDetailPUI.ORDER_STATUS_DROPDOWN, status);
    }

    public void clickToAddHistoryButton() {
//        waitElementClickable(driver, AdminOrderDetailPUI.ADD_HISTORY_BUTTON);
        scrollToBottomPage(driver);
        clickToElementByJS(driver, AdminOrderDetailPUI.ADD_HISTORY_BUTTON);
    }

    public String getFirstStatusInHistoryTable(String column) {
        waitListElementVisible(driver, AdminOrderDetailPUI.DYMAMIC_COLUMNS_BEFORE_BY_NAME, column);
        int columnIndex = getListElementNumber(driver, AdminOrderDetailPUI.DYMAMIC_COLUMNS_BEFORE_BY_NAME, column) + 1;
        waitElementVisible(driver, AdminOrderDetailPUI.DYNAMIC_COLUMN_OF_FIRST_ROW_IN_HISTORY_TABLE, String.valueOf(columnIndex));
        return getElementText(driver, AdminOrderDetailPUI.DYNAMIC_COLUMN_OF_FIRST_ROW_IN_HISTORY_TABLE, String.valueOf(columnIndex));
    }

    public AdminOrdersPO clickToBackButton() {
        scrollToTopPage(driver);
        clickToElementByJS(driver, AdminOrderDetailPUI.BACK_BUTTON);
        return PageGenerator.getPage(AdminOrdersPO.class, driver);
    }
}
