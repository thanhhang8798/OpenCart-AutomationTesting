package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserCartCheckoutPUI;
import com.opencart.core.BasePage;
import org.openqa.selenium.WebDriver;

public class UserCartCheckoutPO extends BasePage {
    WebDriver driver;

    public UserCartCheckoutPO(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseNewAddressRadioButton() {
        waitElementClickable(driver, UserCartCheckoutPUI.NEW_ADDRESS_RADIO);
        checkToCheckboxRadio(driver, UserCartCheckoutPUI.NEW_ADDRESS_RADIO);
    }

    public void enterToTextboxByName(String name, String sendKey) {
        waitElementVisible(driver, UserCartCheckoutPUI.TEXTBOX_BY_NAME, name);
        sendKeyToElement(driver, UserCartCheckoutPUI.TEXTBOX_BY_NAME, sendKey, name);
    }

    public void clickToContinueButton() {
        waitElementClickable(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON);
        clickToElement(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON);
    }

    public void chooseItemInCountryDropdown(String country) {
        waitElementVisible(driver, UserCartCheckoutPUI.COUNTRY_DROPDOWN);
        selectItemInDropdown(driver, UserCartCheckoutPUI.COUNTRY_DROPDOWN, country);
    }

    public void chooseItemInRegionDropdown(String region) {
        waitElementVisible(driver, UserCartCheckoutPUI.REGION_DROPDOWN);
        selectItemInDropdown(driver, UserCartCheckoutPUI.REGION_DROPDOWN, region);
    }

    public void clickToChooseShippingMethod() {
        waitElementClickable(driver, UserCartCheckoutPUI.CHOOSE_SHIPPING_METHOD_BUTTON);
        clickToElement(driver, UserCartCheckoutPUI.CHOOSE_SHIPPING_METHOD_BUTTON);
    }

    public double getShippingRate() {
        waitElementVisible(driver, UserCartCheckoutPUI.SHIPPING_RATE_TEXT_IN_SHIPPING_METHOD_POPUP);
        return Double.parseDouble(getElementText(driver, UserCartCheckoutPUI.SHIPPING_RATE_TEXT_IN_SHIPPING_METHOD_POPUP)
                .split("\\$")[1].replace(",",""));
    }

    public void clickToChoosePaymentMethod() {
        waitElementClickable(driver, UserCartCheckoutPUI.CHOOSE_PAYMENT_METHOD_BUTTON);
        clickToElement(driver, UserCartCheckoutPUI.CHOOSE_PAYMENT_METHOD_BUTTON);
    }

    public void clickToContinueButtonInShippingMethodPopup() {
        waitElementClickable(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON_IN_SHIPPING_METHOD_POPUP);
        clickToElement(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON_IN_SHIPPING_METHOD_POPUP);
    }

    public String getShippingMethodTextInPopup() {
        waitElementVisible(driver, UserCartCheckoutPUI.SHIPPING_RATE_TEXT_IN_SHIPPING_METHOD_POPUP);
        return getElementText(driver, UserCartCheckoutPUI.SHIPPING_RATE_TEXT_IN_SHIPPING_METHOD_POPUP);
    }

    public String getShippingMethodText(String value) {
        waitElementVisible(driver, UserCartCheckoutPUI.SHIPPING_METHOD_TEXT);
        return getElementDOMProperty(driver, UserCartCheckoutPUI.SHIPPING_METHOD_TEXT, value);
    }

    public String getPaymentMethodTextInPopup() {
        waitElementVisible(driver, UserCartCheckoutPUI.OPTION_IN_PAYMENT_METHOD_POPUP);
        return getElementText(driver, UserCartCheckoutPUI.OPTION_IN_PAYMENT_METHOD_POPUP);
    }

    public void clickToContinueButtonInPaymentMethodPopup() {
        waitElementClickable(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON_IN_PAYMENT_METHOD_POPUP);
        clickToElement(driver, UserCartCheckoutPUI.COUNTINUE_BUTTON_IN_PAYMENT_METHOD_POPUP);
    }

    public String getPaymentMethodText(String value) {
        waitElementVisible(driver, UserCartCheckoutPUI.PAYMENT_METHOD_TEXT);
        return getElementDOMProperty(driver, UserCartCheckoutPUI.PAYMENT_METHOD_TEXT, value);
    }

    public double getFlatShippingRate() {
        waitElementVisible(driver, UserCartCheckoutPUI.PLAT_SHIPPING_RATE);
        return Double.parseDouble(getElementText(driver, UserCartCheckoutPUI.PLAT_SHIPPING_RATE)
                .replace("$","").replace(",",""));
    }

    public void clickToConfirmOrderButton() {
        scrollToBottomPage(driver);
        clickToElementByJS(driver, UserCartCheckoutPUI.CONFIRM_ORDER_BUTTON);
    }

    public String getOrderSuccessContent() {
        waitElementVisible(driver, UserCartCheckoutPUI.ORDER_SUCCESS_CONTENT_TEXT);
        return getElementText(driver, UserCartCheckoutPUI.ORDER_SUCCESS_CONTENT_TEXT);
    }


}
