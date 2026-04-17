package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserHomePUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToSearchTextbox(String productName) {
        waitElementVisible(driver, UserHomePUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, UserHomePUI.SEARCH_TEXTBOX, productName);
    }

    public void clickToSearchIcon() {
        waitElementClickable(driver, UserHomePUI.SEARCH_ICON);
        clickToElement(driver, UserHomePUI.SEARCH_ICON);
    }

    public boolean isNewProductDisplayed(String productName) {
        List<WebElement> productNames = getListElement(driver, UserHomePUI.PRODUCT_NAMES);
        for(WebElement e : productNames) {
            String name = e.getText();
            name.equals(productName);
        }
        return true;
    }
}
