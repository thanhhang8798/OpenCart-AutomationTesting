package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserProductPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserProductPO extends BasePage {
    WebDriver driver;

    public UserProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemNumberTextAtCartButton() {
        waitElementVisible(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON);
        return Integer.parseInt(getElementText(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON).trim().split(" ")[0]);
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

    public double getPriceTextAtCartButton() {
        waitElementVisible(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON);
        return Double.parseDouble(getElementText(driver, UserProductPUI.ADD_TO_CART_INFOR_BUTTON)
                .split("\\$")[1].replace(",",""));
    }

    public boolean isItemAddedToCardDisplayed(String productName) {
        waitListElementVisible(driver, UserProductPUI.LIST_PRODUCT_NAME_IN_CART_DROPDOWN);
        List<WebElement> prodNames = getListElement(driver, UserProductPUI.LIST_PRODUCT_NAME_IN_CART_DROPDOWN);
        for (WebElement e : prodNames) {
            String name = e.getText();
            if (name.equals(productName))
                return true;
        }
        return false;
    }

    public double getTotalPriceInCartDropdown() {
        waitElementVisible(driver, UserProductPUI.TOTAL_PRICE_IN_CART_DROPDOWN);
        return Double.parseDouble(getElementText(driver, UserProductPUI.TOTAL_PRICE_IN_CART_DROPDOWN)
                .replace("$","").replace(",",""));
    }

    public double sumAllPriceInCartDropdown() {
        waitListElementVisible(driver, UserProductPUI.LIST_PRICE_IN_CART_DROPDOWN);
        List<WebElement> priceList = getListElement(driver, UserProductPUI.LIST_PRICE_IN_CART_DROPDOWN);
        double total = 0;
        for (WebElement e : priceList) {
            double price = Double.parseDouble(e.getText().replace("$","").replace(",",""));
            total += price;
        }
        return total;
    }
}
