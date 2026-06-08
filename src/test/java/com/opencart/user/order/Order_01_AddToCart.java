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

        userProductDetailPage = userProductPage.clickToProductByName(productName);
        int numberItemBeforeAddToCart = userProductDetailPage.getItemNumberTextAtCartButton();
        double priceBeforeAddToCart = userProductDetailPage.getPriceTextAtCartButton();

        userProductDetailPage.clickToAddToCartButton();
        verifyEquals(userProductDetailPage.getSuccessMessageText(userDriver), "Success: You have added " + productName + " to your shopping cart!");
        userProductDetailPage.waitMessageAlertDisappeared(userDriver);

        int numberItemAfterAddToCart = userProductDetailPage.getItemNumberTextAtCartButton();
        double priceAfterAddToCart = userProductDetailPage.getPriceTextAtCartButton();

        verifyTrue(numberItemAfterAddToCart == numberItemBeforeAddToCart + 1);
        verifyTrue(priceAfterAddToCart == priceBeforeAddToCart + productPrice);
    }

    @Test
    public void Order_02_DisplayItemsInCartDropdown() {
        userProductDetailPage.clickToCartButton(userDriver);
        verifyTrue(userProductDetailPage.isItemAddedToCardDisplayed(productName));

        double totalPrice = userProductDetailPage.getTotalPriceInCartDropdown();
        double sumPrice = userProductDetailPage.sumAllPriceInCartDropdown();
        verifyEquals(totalPrice, sumPrice);
    }

    @Test
    public void Order_03_ViewCart() {
        userShoppingCartPage = userProductDetailPage.clickToViewCart(userDriver);
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
    private UserProductDetailPO userProductDetailPage;
}
