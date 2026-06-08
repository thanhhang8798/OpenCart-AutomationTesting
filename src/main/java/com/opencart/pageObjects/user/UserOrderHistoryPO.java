package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserOrderHistoryPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class UserOrderHistoryPO extends BasePage {
    WebDriver driver;

    public UserOrderHistoryPO(WebDriver driver) {
        this.driver = driver;
    }

    public int getOrderNumber(String column) {
        int columnIndex = getListElementNumber(driver, UserOrderHistoryPUI.DYNAMIC_COLUMNS_BEFORE_COLUMN_TITLE, column) + 1;
        waitElementVisible(driver, UserOrderHistoryPUI.DYNAMIC_COLUMN_OF_FIRST_ROW, String.valueOf(columnIndex));
        return Integer.parseInt(getElementText(driver, UserOrderHistoryPUI.DYNAMIC_COLUMN_OF_FIRST_ROW, String.valueOf(columnIndex)).replace("#",""));
    }

    public String getOrderStatus(String column) {
        waitElementVisible(driver, UserOrderHistoryPUI.DYNAMIC_COLUMNS_BEFORE_COLUMN_TITLE, column);
        int columnIndex = getListElementNumber(driver, UserOrderHistoryPUI.DYNAMIC_COLUMNS_BEFORE_COLUMN_TITLE, column) + 1;
        waitElementVisible(driver, UserOrderHistoryPUI.DYNAMIC_COLUMN_OF_FIRST_ROW, String.valueOf(columnIndex));
        return getElementText(driver, UserOrderHistoryPUI.DYNAMIC_COLUMN_OF_FIRST_ROW, String.valueOf(columnIndex));
    }
}
