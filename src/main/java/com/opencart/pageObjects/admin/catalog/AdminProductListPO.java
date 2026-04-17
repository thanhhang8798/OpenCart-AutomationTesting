package com.opencart.pageObjects.admin.catalog;

import com.opencart.PageUIs.admin.catalog.AdminProductListPUI;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.LeftMenuPageNavigationMenuPO;
import com.opencart.utilities.MySQLConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminProductListPO extends LeftMenuPageNavigationMenuPO {
    private WebDriver driver;

    public AdminProductListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToProductNameTextbox(String productName) {
        waitElementVisible(driver, AdminProductListPUI.PRODUCT_NAME_TEXTBOX);
        sendKeyToElement(driver, AdminProductListPUI.PRODUCT_NAME_TEXTBOX, productName);
    }

    public void clickToFilterButton() {
        scrollToElementOnTop(driver, AdminProductListPUI.FILTER_BUTTON);
        waitElementClickable(driver, AdminProductListPUI.FILTER_BUTTON);
        clickToElement(driver, AdminProductListPUI.FILTER_BUTTON);
        sleepInSecond(2);
    }

    public boolean isProductNameSearchResultDisplayed(String productName) {
        waitListElementVisible(driver, AdminProductListPUI.DYNAMIC_PRODUCT_NAME_SEARCH_RESULT, productName);
        List<WebElement> productNameResult = getListElement(driver, AdminProductListPUI.DYNAMIC_PRODUCT_NAME_SEARCH_RESULT, productName);
        List<String> productNameRow = new ArrayList<String>();

        for (WebElement result : productNameResult) {
            productNameRow.add(result.getText());
        }
        return productNameRow.size() > 0 && productNameRow.get(0).contains(productName);
    }

    public void clickToResetButton() {
        clickToElementByJS(driver, AdminProductListPUI.RESET_SEARCH_BUTTON);
    }

    public void enterToModelTextbox(String modelSearch) {
        waitListElementVisible(driver, AdminProductListPUI.MODEL_TEXTBOX);
        sendKeyToElement(driver, AdminProductListPUI.MODEL_TEXTBOX, modelSearch);
    }


    public String getModelSearchNoResult() {
        waitElementVisible(driver, AdminProductListPUI.NO_RESULT_TEXT);
        return getElementText(driver, AdminProductListPUI.NO_RESULT_TEXT);
    }

    public void selectItemInCategoriesDropdown(String categorySearch) {
        waitElementClickable(driver, AdminProductListPUI.CATEGORIES_PARENT);
        selectItemInCustomDropdown(driver, AdminProductListPUI.CATEGORIES_PARENT, AdminProductListPUI.CATEGORIES_CHILD, categorySearch);
    }

    public void enterToPriceFrom(String priceFrom) {
        waitElementVisible(driver, AdminProductListPUI.PRICE_FROM_TEXTBOX);
        sendKeyToElement(driver, AdminProductListPUI.PRICE_FROM_TEXTBOX, priceFrom);
    }

    public void enterToPriceTo(String priceTo) {
        waitElementVisible(driver, AdminProductListPUI.PRICE_TO_TEXTBOX);
        sendKeyToElement(driver, AdminProductListPUI.PRICE_TO_TEXTBOX, priceTo);
    }

    public List<String> getProductsSearchResultOnUI() {
        waitElementVisible(driver, AdminProductListPUI.PRODUCT_NAME_SEARCH_BY_INDEX);
        List<WebElement> elements = getListElement(driver, AdminProductListPUI.PRODUCT_NAME_SEARCH_BY_INDEX);
        List<String> productNames = new ArrayList<>();
        for (WebElement e : elements) {
            productNames.add(e.getText().trim());
        }
        return productNames;
    }

    public List<String> getProductsByCategoryOnDB(String categoryName) {
        List<String> productNameByCategory = new ArrayList<>();
        String sql = "SELECT pd.name from oc_product_description pd join oc_product_to_category pc on pd.product_id = pc.product_id join oc_category_description cd on pc.category_id = cd.category_id WHERE cd.name = ?";
        try {
            Connection conn = MySQLConfig.getMySQLConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, categoryName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                productNameByCategory.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return productNameByCategory;
    }

    public List<String> getProductsByManufacturerOnDB(String manufacturerName) {
        List<String> productNameByManufacturer = new ArrayList<>();
        String sql = "SELECT pd.name from oc_product_description pd join oc_product pr on pd.product_id = pr.product_id join oc_manufacturer mf on mf.manufacturer_id = pr.manufacturer_id WHERE mf.name = ?";
        try {
            Connection conn = MySQLConfig.getMySQLConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, manufacturerName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                productNameByManufacturer.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return productNameByManufacturer;
    }

    public void selectItemInManufacturerDropdown(String manufacturerItem) {
        waitElementClickable(driver, AdminProductListPUI.MANUFACTURER_PARENT);
        selectItemInCustomDropdown(driver, AdminProductListPUI.MANUFACTURER_PARENT, AdminProductListPUI.MANUFACTURER_CHILD, manufacturerItem);
    }

    public boolean isPriceSearchResultDisplayed(double priceFrom, double priceTo) {
        waitListElementVisible(driver, AdminProductListPUI.PRICE_SEARCH_RESULT);
        List<WebElement> priceElements = getListElement(driver, AdminProductListPUI.PRICE_SEARCH_RESULT);
        for (WebElement e : priceElements) {
            String priceText = e.getText().replace(",","").replace("$","");
            double price = Double.parseDouble(priceText);
            if (price < priceFrom || price > priceTo)
                return false;
        }
        return true;
    }

    public void enterToQuantityFrom(String quantityFrom) {
        waitElementVisible(driver, AdminProductListPUI.QUANTITY_FROM_TEXTBOX);
        sendKeyToElement(driver, AdminProductListPUI.QUANTITY_FROM_TEXTBOX, quantityFrom);
    }

    public boolean isQuantitySearchResultDisplayed(int quantityFrom) {
        waitListElementVisible(driver, AdminProductListPUI.QUANTITY_SEARCH_RESULT);
        List<WebElement> quantityElements = getListElement(driver, AdminProductListPUI.QUANTITY_SEARCH_RESULT);
        for (WebElement e : quantityElements) {
            String quantityText = e.getText();
            int price = Integer.parseInt(quantityText);
            if (price < quantityFrom)
                return false;
        }
        return true;
    }

    public void selectItemInStatusrDropdown(String statusSearch) {
        waitElementVisible(driver, AdminProductListPUI.STATUS_DROPDOWN);
        selectItemInDropdown(driver, AdminProductListPUI.STATUS_DROPDOWN, statusSearch);
    }

    public List<String> getProductsByStatusOnDB() {
        List<String> productNameByStatus = new ArrayList<>();
        String sql = "SELECT pd.name from oc_product_description pd join oc_product pr on pd.product_id = pr.product_id WHERE pr.status=0";
        try {
            Connection conn = MySQLConfig.getMySQLConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                productNameByStatus.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return productNameByStatus;
    }

    public void checkToAllItemsCheckbox() {
        waitElementClickable(driver, AdminProductListPUI.CHECKBOX_AT_TABLEHEADER);
        checkToCheckboxRadio(driver, AdminProductListPUI.CHECKBOX_AT_TABLEHEADER);
    }

    public boolean isAllItemsCheckboxSelected() {
        List<WebElement> checkboxSelected = getListElement(driver, AdminProductListPUI.ALL_ITEM_CHECKBOX);
        for (WebElement checkbox : checkboxSelected){
            checkbox.isSelected();
        }
        return true;
    }

    public void uncheckToAllItemsCheckbox() {
        waitElementClickable(driver, AdminProductListPUI.CHECKBOX_AT_TABLEHEADER);
        uncheckToCheckboxRadio(driver, AdminProductListPUI.CHECKBOX_AT_TABLEHEADER);
    }

    public void checkToOneCheckbox(String oneItemCheckbox) {
        waitElementClickable(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
        checkToCheckboxRadio(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
    }

    public boolean isItemCheckboxSelected(String oneItemCheckbox) {
        waitElementSelected(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
        return isElementSelected(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
    }

    public void uncheckToOneItemCheckbox(String oneItemCheckbox) {
        waitElementClickable(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
        uncheckToCheckboxRadio(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
    }

    public boolean isItemCheckboxUnselected(String oneItemCheckbox) {
        return !isElementSelected(driver, AdminProductListPUI.DYNAMIC_ONE_ITEM_CHECKBOX, oneItemCheckbox);
    }

    public void clickToCloneButton() {
        clickToElementByJS(driver, AdminProductListPUI.CLONE_BUTTON);
    }

    public String getSuccessMessageText() {
        waitElementVisible(driver, AdminProductListPUI.SUCCESS_ALERT);
        return getElementText(driver, AdminProductListPUI.SUCCESS_ALERT).trim();
    }

    public void clickToDeleteButton() {
        waitElementClickable(driver, AdminProductListPUI.DELETE_BUTTON);
        clickToElement(driver, AdminProductListPUI.DELETE_BUTTON);
    }

    public void acceptToDeleteAlert() {
        waitAlertPresence(driver);
        acceptToAlert(driver);
    }

    public int getListProductSize(String item) {
        waitListElementVisible(driver, AdminProductListPUI.DYNAMIC_PRODUCT_NAME_SEARCH_RESULT, item);
        return getListElement(driver, AdminProductListPUI.DYNAMIC_PRODUCT_NAME_SEARCH_RESULT, item).size();
    }

    public AdminProductFormPO clickToAddNewItemButton() {
        waitElementClickable(driver, AdminProductListPUI.ADD_NEW_ITEM_BUTTON);
        clickToElement(driver, AdminProductListPUI.ADD_NEW_ITEM_BUTTON);
        return PageGenerator.getPage(AdminProductFormPO.class, driver);
    }


    public AdminProductFormPO clickToEditItemButton(String item) {
        waitElementClickable(driver, AdminProductListPUI.DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME, item);
        clickToElement(driver, AdminProductListPUI.DYNAMIC_EDIT_BUTTON_BY_PRODUCT_NAME, item);
        return PageGenerator.getPage(AdminProductFormPO.class, driver);
    }

}
