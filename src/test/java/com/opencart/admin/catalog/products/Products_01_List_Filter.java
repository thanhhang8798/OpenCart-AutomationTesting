package com.opencart.admin.catalog.products;

import com.opencart.core.BaseTest;
import com.opencart.core.GlobalConstants;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminDashboardPO;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.pageObjects.admin.catalog.AdminProductListPO;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.List;

public class Products_01_List_Filter extends BaseTest {
    private WebDriver driver;
    private PropertiesConfig propertiesConfig;
    String productNameSearch, modelSearch, categorySearch, priceTo, priceFrom, manufacturerSearch, quantityFrom, statusSearch;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        driver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUserNameTextbox(GlobalConstants.ADMIN_USERNAME);
        adminLoginPage.enterToPasswordTextbox(GlobalConstants.ADMIN_PASSWORD);
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        adminDashboardPage.openPageOnLeftMenuByName("Catalog");
        adminDashboardPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,driver);

        productNameSearch = "iPod";
        modelSearch = "mm";
        categorySearch = "Cameras";
        manufacturerSearch = "Canon";
        priceFrom = "200";
        priceTo = "300";
        quantityFrom = "1000";
        statusSearch = "Disabled";
    }

    @Test
    public void Products_01_Filter_ProductName() {
        adminProductListPage.enterToProductNameTextbox(productNameSearch);
        adminProductListPage.clickToFilterButton();
        verifyTrue(adminProductListPage.isProductNameSearchResultDisplayed(productNameSearch));
    }

    @Test
    public void Products_02_Filter_Model_NoResult() {
        adminProductListPage.clickToResetButton();
        adminProductListPage.enterToModelTextbox(modelSearch);
        adminProductListPage.clickToFilterButton();
        verifyEquals(adminProductListPage.getModelSearchNoResult(), "No results!");
    }

    @Test
    public void Products_03_Filter_Categories() {
        adminProductListPage.clickToResetButton();
        adminProductListPage.selectItemInCategoriesDropdown(categorySearch);
        adminProductListPage.clickToFilterButton();
        List<String> actualProducts = adminProductListPage.getProductsSearchResultOnUI();
        List<String> expectedProducts = adminProductListPage.getProductsByCategoryOnDB(categorySearch);
        verifyTrue(actualProducts.containsAll(expectedProducts));
    }

    @Test
    public void Products_04_Filter_Quantity() {
        adminProductListPage.clickToResetButton();
        adminProductListPage.enterToQuantityFrom(quantityFrom);
        adminProductListPage.clickToFilterButton();
        verifyTrue(adminProductListPage.isQuantitySearchResultDisplayed(Integer.parseInt(priceFrom)));
    }

    @Test
    public void Products_05_Filter_Status() {
        adminProductListPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,driver);
        adminProductListPage.selectItemInStatusrDropdown(statusSearch);
        adminProductListPage.clickToFilterButton();
        List<String> actualProducts = adminProductListPage.getProductsSearchResultOnUI();
        List<String> expectedProducts = adminProductListPage.getProductsByStatusOnDB();
        verifyTrue(actualProducts.containsAll(expectedProducts));
    }

    @Test
    public void Products_06_Filter_Manufacturer() {
        adminProductListPage.clickToResetButton();
        adminProductListPage.selectItemInManufacturerDropdown(manufacturerSearch);
        adminProductListPage.clickToFilterButton();
        List<String> actualProducts = adminProductListPage.getProductsSearchResultOnUI();
        List<String> expectedProducts = adminProductListPage.getProductsByManufacturerOnDB(manufacturerSearch);
        verifyTrue(actualProducts.containsAll(expectedProducts));
    }

    @Test
    public void Products_07_Filter_Price() {
        adminProductListPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,driver);
        adminProductListPage.enterToPriceFrom(priceFrom);
        adminProductListPage.enterToPriceTo(priceTo);
        adminProductListPage.clickToFilterButton();
        verifyTrue(adminProductListPage.isPriceSearchResultDisplayed(Double.parseDouble(priceFrom), Double.parseDouble(priceTo)));
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminProductListPO adminProductListPage;

}
