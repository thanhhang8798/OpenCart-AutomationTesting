package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserShoppingCartPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserShoppingCartPO extends BasePage {
    WebDriver driver;

    public UserShoppingCartPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemAddedToCardDisplayed(String productName) {
        waitListElementVisible(driver, UserShoppingCartPUI.COLUMNS_BEFORE_PRODUCT_COLUMN);
        int productNameIndex = getListElementNumber(driver, UserShoppingCartPUI.COLUMNS_BEFORE_PRODUCT_COLUMN) + 1;

        waitListElementVisible(driver, UserShoppingCartPUI.LIST_PRODUCT_NAME_COLUMN, String.valueOf(productNameIndex));
        List<WebElement> productNameElements = getListElement(driver, UserShoppingCartPUI.LIST_PRODUCT_NAME_COLUMN, String.valueOf(productNameIndex));
        for (WebElement e : productNameElements) {
            String name = e.getText();
            if (name.equals(productName))
                return true;
        }
        return false;
    }

    public double getTotalPriceInCartDropdown() {
        waitElementVisible(driver, UserShoppingCartPUI.TOTAL_PRICE_TEXT);
        return Double.parseDouble(getElementText(driver, UserShoppingCartPUI.TOTAL_PRICE_TEXT)
                .replace("$","").replace(",",""));
    }

    public double sumAllPriceInCartDropdown() {
        waitListElementVisible(driver, UserShoppingCartPUI.COLUMNS_BEFORE_TOTAL_COLUMN);
        int priceColumnIndex = getListElementNumber(driver, UserShoppingCartPUI.COLUMNS_BEFORE_TOTAL_COLUMN) + 1;

        float sum = 0;
        waitListElementVisible(driver, UserShoppingCartPUI.LIST_PRICE_COLUMN, String.valueOf(priceColumnIndex));
        List<WebElement> priceElements = getListElement(driver, UserShoppingCartPUI.LIST_PRICE_COLUMN, String.valueOf(priceColumnIndex));
        for (WebElement e : priceElements) {
            double price = Double.parseDouble(e.getText().replace("$","").replace(",",""));
            sum += price;
        }
        return sum;
    }
}
