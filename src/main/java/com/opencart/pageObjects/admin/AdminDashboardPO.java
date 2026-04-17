package com.opencart.pageObjects.admin;

import com.opencart.PageUIs.admin.AdminDashboardPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPO extends LeftMenuPageNavigationMenuPO {
    private WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDashboardTitleDisplayed() {
        waitElementVisible(driver, AdminDashboardPUI.DASHBOARD_TITLE);
        return isElementDisplayed(driver, AdminDashboardPUI.DASHBOARD_TITLE);
    }
}
