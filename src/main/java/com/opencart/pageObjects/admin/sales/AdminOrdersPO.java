package com.opencart.pageObjects.admin.sales;

import com.opencart.PageUIs.admin.sales.AdminOrderDetailPUI;
import com.opencart.PageUIs.admin.sales.AdminOrdersPUI;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.LeftMenuPageNavigationMenuPO;
import org.openqa.selenium.WebDriver;

public class AdminOrdersPO extends LeftMenuPageNavigationMenuPO {
    WebDriver driver;

    public AdminOrdersPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AdminOrderDetailPO clickToViewButtonByOrderId(String orderId) {
        waitElementClickable(driver, AdminOrdersPUI.DYNAMIC_VIEW_ORDER_BY_ID, orderId);
        clickToElement(driver, AdminOrdersPUI.DYNAMIC_VIEW_ORDER_BY_ID, orderId);
        return PageGenerator.getPage(AdminOrderDetailPO.class, driver);
    }

    public String getOrderStatusByOrderId(String orderId) {
        waitElementVisible(driver, AdminOrdersPUI.DYNAMIC_ORDER_STATUS_BY_ID, orderId);
        return getElementText(driver, AdminOrdersPUI.DYNAMIC_ORDER_STATUS_BY_ID, orderId);
    }
}
