package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserMyAccountPUI;
import com.opencart.PageUIs.user.UserProductDetailPUI;
import com.opencart.PageUIs.user.UserProductPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserProductDetailPO extends BasePage {
    WebDriver driver;

    public UserProductDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemNumberTextAtCartButton() {
        waitElementVisible(driver, UserProductDetailPUI.ADD_TO_CART_INFOR_BUTTON);
        return Integer.parseInt(getElementText(driver, UserProductDetailPUI.ADD_TO_CART_INFOR_BUTTON).trim().split(" ")[0]);
    }

    public double getPriceTextAtCartButton() {
        waitElementVisible(driver, UserProductDetailPUI.ADD_TO_CART_INFOR_BUTTON);
        return Double.parseDouble(getElementText(driver, UserProductDetailPUI.ADD_TO_CART_INFOR_BUTTON)
                .split("\\$")[1].replace(",",""));
    }

    public boolean isItemAddedToCardDisplayed(String productName) {
        waitListElementVisible(driver, UserProductDetailPUI.LIST_PRODUCT_NAME_IN_CART_DROPDOWN);
        List<WebElement> prodNames = getListElement(driver, UserProductDetailPUI.LIST_PRODUCT_NAME_IN_CART_DROPDOWN);
        for (WebElement e : prodNames) {
            String name = e.getText();
            if (name.equals(productName))
                return true;
        }
        return false;
    }

    public double getTotalPriceInCartDropdown() {
        waitElementVisible(driver, UserProductDetailPUI.TOTAL_PRICE_IN_CART_DROPDOWN);
        return Double.parseDouble(getElementText(driver, UserProductDetailPUI.TOTAL_PRICE_IN_CART_DROPDOWN)
                .replace("$","").replace(",",""));
    }

    public double sumAllPriceInCartDropdown() {
        waitListElementVisible(driver, UserProductDetailPUI.LIST_PRICE_IN_CART_DROPDOWN);
        List<WebElement> priceList = getListElement(driver, UserProductDetailPUI.LIST_PRICE_IN_CART_DROPDOWN);
        double total = 0;
        for (WebElement e : priceList) {
            double price = Double.parseDouble(e.getText().replace("$","").replace(",",""));
            total += price;
        }
        return total;
    }

    public void clickToAddToCartButton() {
        waitElementClickable(driver, UserProductDetailPUI.ADD_TO_CART_BUTTON);
        clickToElement(driver, UserProductDetailPUI.ADD_TO_CART_BUTTON);
    }

    public UserCartCheckoutPO clickToCheckoutInCartDropdown() {
        waitElementClickable(driver, UserProductDetailPUI.CHECKOUT_IN_CART_DROPDOWN);
        clickToElement(driver, UserProductDetailPUI.CHECKOUT_IN_CART_DROPDOWN);
        return PageGenerator.getPage(UserCartCheckoutPO.class, driver);
    }
}
