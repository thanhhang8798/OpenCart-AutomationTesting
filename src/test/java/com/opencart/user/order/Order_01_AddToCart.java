package com.opencart.user.order;

import com.opencart.core.BaseTest;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.pageObjects.user.*;
import com.opencart.utilities.DataFakerConfig;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Order_01_AddToCart extends BaseTest {
    private WebDriver userDriver;
    private PropertiesConfig propertiesConfig;
    String productName, productMenu, productContainer;
    double productPrice;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        userDriver = getBrowserDriver(propertiesConfig.getApplicationUserUrl(), browser);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        userLoginPage = userHomePage.clickToLoginInDropdown();
        userLoginPage.enterToEmailAddressTextbox(propertiesConfig.getUserEmailAddress());
        userLoginPage.enterToEmailPasswordTextbox(propertiesConfig.getUserPassword());
        userMyAccountPage = userLoginPage.clickToLoginButton();

        productName = "iMac";
        productMenu = "Mac (1)";
        productContainer = "Desktops";
    }

    @Test()
    public void Order_01_AddToCart() {
        userProductPage = userMyAccountPage.chooseProductAtMenu(productContainer, productMenu);
        productPrice = userProductPage.getProductPrice(productName);

        int numberItemBeforeAddToCart = userProductPage.getItemNumberTextAtCartButton();
        double priceBeforeAddToCart = userProductPage.getPriceTextAtCartButton();

        userProductPage.clickToAddToCartByProductName(productName);
        verifyTrue(userProductPage.isAddToCartSuccessAlertDisplayed());
        userProductPage.sleepInSecond(2);

        int numberItemAfterAddToCart = userProductPage.getItemNumberTextAtCartButton();
        double priceAfterAddToCart = userProductPage.getPriceTextAtCartButton();

        verifyTrue(numberItemAfterAddToCart == numberItemBeforeAddToCart + 1);
        verifyTrue(priceAfterAddToCart == priceBeforeAddToCart + productPrice);
    }

    @Test
    public void Order_02_DisplayItemsInCartDropdown() {
        userProductPage.clickToCartButton(userDriver);
        verifyTrue(userProductPage.isItemAddedToCardDisplayed(productName));

        double totalPrice = userProductPage.getTotalPriceInCartDropdown();
        double sumPrice = userProductPage.sumAllPriceInCartDropdown();
        verifyEquals(totalPrice, sumPrice);
    }

    @Test
    public void Order_03_ViewCart() {
        userShoppingCartPage = userProductPage.clickToViewCart(userDriver);
        verifyTrue(userShoppingCartPage.isItemAddedToCardDisplayed(productName));

        double totalPrice = userShoppingCartPage.getTotalPriceInCartDropdown();
        double sumPrice = userShoppingCartPage.sumAllPriceInCartDropdown();
        verifyEquals(totalPrice, sumPrice);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }

    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private UserProductPO userProductPage;
    private UserShoppingCartPO userShoppingCartPage;
    private AdminLoginPO adminLoginPage;
}
