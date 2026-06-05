package com.opencart.user.order;

import com.opencart.core.BaseTest;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.pageObjects.user.UserHomePO;
import com.opencart.pageObjects.user.UserLoginPO;
import com.opencart.pageObjects.user.UserMyAccountPO;
import com.opencart.pageObjects.user.UserProductPO;
import com.opencart.utilities.DataFakerConfig;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Order_01_OrderProduct extends BaseTest {
    private WebDriver adminDriver;
    private WebDriver userDriver;
    private PropertiesConfig propertiesConfig;
    private DataFakerConfig dataFakerConfig;
    String productName, productMenu, productContainer;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        userDriver = getBrowserDriver(propertiesConfig.getApplicationUserUrl(), browser);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

//        adminDriver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
//        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);


        userLoginPage = userHomePage.clickToLoginInDropdown();
        userLoginPage.enterToEmailAddressTextbox(propertiesConfig.getUserEmailAddress());
        userLoginPage.enterToEmailPasswordTextbox(propertiesConfig.getUserPassword());
        userMyAccountPage = userLoginPage.clickToLoginButton();

        productName = "iMac";
        productMenu = "Mac (1)";
        productContainer = "Desktops";
    }

    @Test
    public void Order_01_AddToCart() {
        userProductPage = userMyAccountPage.chooseProductAtMenu(productContainer, productMenu);
        int numberItemBeforeAddToCart = userProductPage.getAddToCartNumberItemText();

        userProductPage.clickToAddToCartByProductName(productName);
        userProductPage.sleepInSecond(2);

        int numberItemAfterAddToCart = userProductPage.getAddToCartNumberItemText();
        System.out.println("after: " + numberItemAfterAddToCart);

        verifyTrue(numberItemAfterAddToCart == numberItemBeforeAddToCart + 1);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private UserProductPO userProductPage;
    private AdminLoginPO adminLoginPage;
}
