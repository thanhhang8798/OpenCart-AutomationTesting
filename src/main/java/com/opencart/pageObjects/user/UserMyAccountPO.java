package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserMyAccountPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;

public class UserMyAccountPO extends BasePage {
    WebDriver driver;

    public UserMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserHomePO clickToHomeBreadcrumb() {
//        waitElementClickable(driver, UserMyAccountPUI.HOME_BREADCRUMB);
//        clickToElement(driver, UserMyAccountPUI.HOME_BREADCRUMB);
        clickToElementByJS(driver, UserMyAccountPUI.HOME_BREADCRUMB);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserProductPO chooseProductAtMenu(String productContainer, String productMenu) {
        waitElementClickable(driver, UserMyAccountPUI.DYNAMIC_PARENT_PRODUCT_DROPDOWN_BY_NAME, productContainer);
        selectItemInCustomDropdown(driver, UserMyAccountPUI.DYNAMIC_PARENT_PRODUCT_DROPDOWN_BY_NAME, UserMyAccountPUI.CHILD_PRODUCT_DROPDOWN, productMenu, productContainer);
        return PageGenerator.getPage(UserProductPO.class, driver);
    }


}
