package com.opencart.admin.catalog.products;

import com.opencart.core.BaseTest;
import com.opencart.core.GlobalConstants;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminDashboardPO;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.pageObjects.admin.catalog.AdminProductFormPO;
import com.opencart.pageObjects.admin.catalog.AdminProductListPO;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Products_02_List_Actions extends BaseTest {
    private WebDriver adminDriver;
    private PropertiesConfig propertiesConfig;
    String oneItemCheckbox, cloneItem, deleteItem, editItem;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        adminDriver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);

        adminLoginPage.enterToUserNameTextbox(GlobalConstants.ADMIN_USERNAME);
        adminLoginPage.enterToPasswordTextbox(GlobalConstants.ADMIN_PASSWORD);
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        adminDashboardPage.openPageOnLeftMenuByName("Catalog");
        adminDashboardPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class, adminDriver);

        oneItemCheckbox = "HTC Touch HD";
        cloneItem = "HP LP3065";
        deleteItem = "HP LP3065";
        editItem = "iMac";
    }

    @Test
    public void Products_01_Action_SelectAllItems() {
        adminProductListPage.checkToAllItemsCheckbox();
        verifyTrue(adminProductListPage.isAllItemsCheckboxSelected());
        adminProductListPage.uncheckToAllItemsCheckbox();
    }

    @Test
    public void Products_02_Action_SelectOneItem() {
        adminProductListPage.checkToOneCheckbox(oneItemCheckbox);
        verifyTrue(adminProductListPage.isItemCheckboxSelected(oneItemCheckbox));
        adminProductListPage.uncheckToOneItemCheckbox(oneItemCheckbox);
        verifyTrue(adminProductListPage.isItemCheckboxUnselected(oneItemCheckbox));
    }

    @Test
    public void Products_03_Action_Clone() {
        adminProductListPage.checkToOneCheckbox(cloneItem);
        adminProductListPage.clickToCloneButton();
        verifyEquals(adminProductListPage.getSuccessMessageText(), "Success: You have modified products!");
        adminProductListPage.sleepInSecond(3);
        verifyTrue(adminProductListPage.getListProductSize(cloneItem) > 1);
    }

    @Test
    public void Products_04_Action_Delete() {
        adminProductListPage.sleepInSecond(3);
        int productsBeforeDeleteSize = adminProductListPage.getListProductSize(deleteItem);
        System.out.println("before " + productsBeforeDeleteSize);
        adminProductListPage.checkToOneCheckbox(deleteItem);
        adminProductListPage.clickToDeleteButton();
        adminProductListPage.acceptToDeleteAlert();
        verifyEquals(adminProductListPage.getSuccessMessageText(), "Success: You have modified products!");
        adminProductListPage.sleepInSecond(3);

        int productsAfterDeleteSize = adminProductListPage.getListProductSize(deleteItem);
        System.out.println("after " + productsAfterDeleteSize);
        verifyTrue(productsBeforeDeleteSize > productsAfterDeleteSize);
    }

    @Test
    public void Products_05_Action_AddNewItem() {
        adminProductFormPage = adminProductListPage.clickToAddNewItemButton();
        verifyEquals(adminProductFormPage.getProductPageHeader(),"Add Product");
        adminProductFormPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,adminDriver);
    }

    @Test
    public void Products_06_Action_EditItem() {
        adminProductFormPage = adminProductListPage.clickToEditItemButton(editItem);
        verifyEquals(adminProductFormPage.getProductPageHeader(),"Edit Product");
        adminProductFormPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,adminDriver);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver(adminDriver);
    }

    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminProductListPO adminProductListPage;
    private AdminProductFormPO adminProductFormPage;
}
