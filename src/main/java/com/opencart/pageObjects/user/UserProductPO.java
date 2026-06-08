package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserProductPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserProductPO extends BasePage {
    WebDriver driver;

    public UserProductPO(WebDriver driver) {
        this.driver = driver;
    }



    public void clickToAddToCartByProductName(String productName) {
        clickToElement(driver, UserProductPUI.DYNAMIC_ADD_TO_CART_BY_NAME, productName);
    }

    public void waitUntilSuccessAlertDisappeared() {
        waitElementInvisible(driver, UserProductPUI.SUCCESS_ALERT);
    }

    public boolean isAddToCartSuccessAlertDisplayed() {
        waitElementVisible(driver, UserProductPUI.SUCCESS_ALERT);
        return isElementDisplayed(driver, UserProductPUI.SUCCESS_ALERT);
    }

    public double getProductPrice(String productName) {
        waitElementVisible(driver, UserProductPUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName);
        return Double.parseDouble(getElementText(driver, UserProductPUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName)
                .replace("$","").replace(",",""));
    }


    public UserProductDetailPO clickToProductByName(String productName) {
        waitElementClickable(driver, UserProductPUI.DYNAMIC_PRODUCT_BY_NAME, productName);
        clickToElement(driver, UserProductPUI.DYNAMIC_PRODUCT_BY_NAME, productName);
        return PageGenerator.getPage(UserProductDetailPO.class, driver);
    }
}
