package com.opencart.admin.catalog.products;

import com.opencart.core.BaseTest;
import com.opencart.core.GlobalConstants;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminDashboardPO;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.pageObjects.admin.catalog.AdminProductFormPO;
import com.opencart.pageObjects.admin.catalog.AdminProductListPO;
import com.opencart.pageObjects.user.UserHomePO;
import com.opencart.utilities.DataFakerConfig;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class Products_03_Form_AddProduct extends BaseTest {
    private WebDriver adminDriver;
    private WebDriver userDriver;
    private PropertiesConfig propertiesConfig;
    private DataFakerConfig faker;
    String productName, megaTagTitle, description, model, price, quantity, manufacturerItem, categoryItem, keywordSEO;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        adminDriver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);

        userDriver = getBrowserDriver(propertiesConfig.getApplicationUserUrl(), browser);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        faker = DataFakerConfig.getFaker();

        adminLoginPage.enterToUserNameTextbox(propertiesConfig.getApplicationUserName());
        adminLoginPage.enterToPasswordTextbox(propertiesConfig.getApplicationPassword());
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        adminDashboardPage.openPageOnLeftMenuByName("Catalog");
        adminDashboardPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(AdminProductListPO.class,adminDriver);
        adminProductFormPage = adminProductListPage.clickToAddNewItemButton();

        productName = "Nokia " + new Random().nextInt(70);
        megaTagTitle = "Nokia";
        description = faker.getDescription();
        model = "product " + new Random().nextInt(100);
        price = "1000";
        quantity = faker.getNumberFiveDigits();
        manufacturerItem = "HTC";
        categoryItem = "Cameras";
        keywordSEO = "Nokia_" + new Random().nextInt(2000);
    }

    @Test
    public void Form_01_AddProduct() {
        adminProductFormPage.enterToTextboxByPlaceholder("Product Name", productName);
        adminProductFormPage.enterToDescriptionTextArea(description);
        adminProductFormPage.enterToTextboxByPlaceholder("Meta Tag Title", megaTagTitle);

        adminProductFormPage.clickToTabByText("Data");
        adminProductFormPage.enterToTextboxByPlaceholder("Model", model);
        adminProductFormPage.enterToTextboxByPlaceholder("Price", price);
        adminProductFormPage.enterToTextboxByPlaceholder("Quantity", quantity);

        adminProductFormPage.clickToTabByText("Links");
        adminProductFormPage.sleepInSecond(1);
        adminProductFormPage.selectItemInManufacturerDropdown(manufacturerItem);
        adminProductFormPage.selectItemInCategoriesDropdown(categoryItem);

        adminProductFormPage.clickToTabByText("SEO");
        adminProductFormPage.enterToTextboxByPlaceholder("Keyword", keywordSEO);
        adminProductFormPage.clickToSaveButton();

        verifyEquals(adminProductFormPage.getSuccessAlertText(), "Success: You have modified products!");
        verifyEquals(adminProductFormPage.getTextByLabel("Keyword"), keywordSEO);

        adminProductFormPage.clickToTabByText("General");
        verifyEquals(adminProductFormPage.getTextByLabel("Product Name"), productName);
        verifyEquals(adminProductFormPage.getTextByLabel("Meta Tag Title"), megaTagTitle);
        verifyTrue(adminProductFormPage.isDescriptionTextDisplay());
        adminProductFormPage.switchToDefaultContent(adminDriver);

        adminProductFormPage.clickToTabByText("Data");
        verifyEquals(adminProductFormPage.getTextByLabel("Model"), model);
        verifyEquals(adminProductFormPage.getTextByLabel("Price"), price);
        verifyEquals(adminProductFormPage.getTextByLabel("Quantity"), quantity);

        adminProductFormPage.clickToTabByText("Links");
        verifyEquals(adminProductFormPage.getManufacturerText(), manufacturerItem);
        verifyEquals(adminProductFormPage.getCategoryText(), categoryItem);
    }

    @Test
    public void Form_02_NewProductAtListProductPage(){
        adminProductFormPage.openPageOnLeftMenuByName("Products");
        adminProductListPage = PageGenerator.getPage(adminProductListPage.getClass(),adminDriver);
        adminProductListPage.enterToProductNameTextbox(productName);
        adminProductListPage.clickToFilterButton();
        verifyTrue(adminProductListPage.isProductNameSearchResultDisplayed(productName));
    }

    @Test
    public void Form_03_NewProductAtUserSite(){
        userHomePage.enterToSearchTextbox(productName);
        userHomePage.clickToSearchIcon();
        verifyTrue(userHomePage.isNewProductDisplayed(productName));
    }
    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminProductListPO adminProductListPage;
    private AdminProductFormPO adminProductFormPage;
    private UserHomePO userHomePage;
}
