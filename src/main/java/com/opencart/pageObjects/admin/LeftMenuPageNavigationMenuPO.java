package com.opencart.pageObjects.admin;

import com.opencart.PageUIs.admin.LeftMenuPageNavigationPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;

public class LeftMenuPageNavigationMenuPO extends BasePage {
    private WebDriver driver;

    public LeftMenuPageNavigationMenuPO(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageOnLeftMenuByName(String pageName) {
        clickToElementByJS(driver, LeftMenuPageNavigationPUI.DYNAMIC_PAGE_NAME_ON_LEFT_MENU, pageName);
    }
}
