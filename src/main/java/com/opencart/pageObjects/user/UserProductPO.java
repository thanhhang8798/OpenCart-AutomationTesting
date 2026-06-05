package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserHomePUI;
import com.opencart.PageUIs.user.UserProductPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;

public class UserProductPO extends BasePage {
    WebDriver driver;

    public UserProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public int getAddToCartNumberItemText() {
        waitElementVisible(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON);
        return Integer.parseInt(getElementText(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON).trim().split(" ")[0]);
    }

    public void clickToAddToCartByProductName(String productName) {
//        scrollToElementOnTop(driver, UserProductPUI.DYNAMIC_ADD_TO_CART_BY_NAME, productName);
//        clickToElement(driver, UserProductPUI.DYNAMIC_ADD_TO_CART_BY_NAME, productName);
        clickToElementByJS(driver, UserProductPUI.DYNAMIC_ADD_TO_CART_BY_NAME, productName);
    }
}
