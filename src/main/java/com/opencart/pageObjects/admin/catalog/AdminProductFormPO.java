package com.opencart.pageObjects.admin.catalog;

import com.opencart.PageUIs.admin.catalog.AdminProductFormPUI;
import com.opencart.PageUIs.admin.catalog.AdminProductListPUI;
import com.opencart.pageObjects.admin.LeftMenuPageNavigationMenuPO;
import org.openqa.selenium.WebDriver;

public class AdminProductFormPO extends LeftMenuPageNavigationMenuPO {
    WebDriver driver;

    public AdminProductFormPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getProductPageHeader() {
        waitElementVisible(driver, AdminProductFormPUI.PRODUCT_HEADER);
        return getElementText(driver, AdminProductFormPUI.PRODUCT_HEADER).trim();
    }

    public void enterToTextboxByPlaceholder(String placeholderName, String sendkey) {
        waitElementVisible(driver, AdminProductFormPUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, placeholderName);
        sendKeyToElement(driver, AdminProductFormPUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, sendkey, placeholderName);
    }

    public void clickToTabByText(String tab) {
        clickToElementByJS(driver, AdminProductFormPUI.DYNAMIC_TAB_BY_TEXT, tab);
    }

    public void selectItemInManufacturerDropdown(String manufacturerItem) {
        scrollToElementOnTop(driver, AdminProductFormPUI.MANUFACTURER_PARENT);
        selectItemInCustomDropdown(driver, AdminProductFormPUI.MANUFACTURER_PARENT, AdminProductFormPUI.MANUFACTURER_CHILD, manufacturerItem);
    }

    public void selectItemInCategoriesDropdown(String categoryItem) {
        selectItemInCustomDropdown(driver, AdminProductFormPUI.CATEGORIES_PARENT, AdminProductFormPUI.CATEGORIES_CHILD, categoryItem);
    }

    public void clickToSaveButton() {
        scrollToElementOnTop(driver, AdminProductFormPUI.SAVE_BUTTON);
        clickToElementByJS(driver, AdminProductFormPUI.SAVE_BUTTON);
    }

    public String getSuccessAlertText() {
        waitElementVisible(driver, AdminProductFormPUI.SUCCESS_ALLERT);
        return getElementText(driver, AdminProductFormPUI.SUCCESS_ALLERT).trim();
    }

    public void enterToDescriptionTextArea(String description) {
        switchToFrame(driver, AdminProductFormPUI.DESCRIPTION_IFRAME);
        sendKeyToElement(driver, AdminProductFormPUI.DESCRIPTION_TEXTAREA, description);
        switchToDefaultContent(driver);
    }

    public String getTextByLabel(String label) {
        waitElementVisible(driver, AdminProductFormPUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, label);
        return getElementDOMProperty(driver, AdminProductFormPUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, "value", label);
    }

    public String getManufacturerText() {
        waitElementVisible(driver, AdminProductFormPUI.MANUFACTURER_TEXT);
        return getElementDOMProperty(driver, AdminProductFormPUI.MANUFACTURER_TEXT, "value");
    }

    public String getCategoryText() {
        waitElementVisible(driver, AdminProductFormPUI.CATEGORY_TEXT);
        return getElementText(driver, AdminProductFormPUI.CATEGORY_TEXT);
    }

    public boolean isDescriptionTextDisplay() {
        switchToFrame(driver, AdminProductFormPUI.DESCRIPTION_IFRAME);
        return isElementDisplayed(driver, AdminProductFormPUI.DESCRIPTION_TEXTAREA);
    }
}
