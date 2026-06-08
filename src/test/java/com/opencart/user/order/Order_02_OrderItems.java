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

public class Order_02_OrderItems extends BaseTest {
    private WebDriver adminDriver;
    private WebDriver userDriver;
    private PropertiesConfig propertiesConfig;
    private DataFakerConfig faker;
    String productName, productMenu, productContainer;
    String firstName, lastName, address1, city, postCode, country, region;
    double productPrice;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        userDriver = getBrowserDriver(propertiesConfig.getApplicationUserUrl(), browser);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);
        faker = DataFakerConfig.getFaker();

//        adminDriver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
//        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);


        userLoginPage = userHomePage.clickToLoginInDropdown();
        userLoginPage.enterToEmailAddressTextbox(propertiesConfig.getUserEmailAddress());
        userLoginPage.enterToEmailPasswordTextbox(propertiesConfig.getUserPassword());
        userMyAccountPage = userLoginPage.clickToLoginButton();

        productName = "iMac";
        productMenu = "Mac (1)";
        productContainer = "Desktops";

        userProductPage = userMyAccountPage.chooseProductAtMenu(productContainer, productMenu);
        userProductDetailPage = userProductPage.clickToProductByName(productName);
        userProductDetailPage.clickToAddToCartButton();
        userProductDetailPage.waitMessageAlertDisappeared(userDriver);

        userProductDetailPage.clickToCartButton(userDriver);
        userProductDetailPage.sleepInSecond(1);
        userCartCheckoutPage = userProductDetailPage.clickToCheckoutInCartDropdown();

        firstName = faker.getFirstName();
        lastName = faker.getLastName();
        country = "Viet Nam";
        city = faker.getCityAddress();
        postCode = "10000";
        region = "An Giang";
        address1 = faker.getStreetAddress();
    }

    @Test()
    public void Order_01_ShippingAddress() {
        userCartCheckoutPage.chooseNewAddressRadioButton();
        userCartCheckoutPage.enterToTextboxByName("firstname", firstName);
        userCartCheckoutPage.enterToTextboxByName("lastname", lastName);
        userCartCheckoutPage.chooseItemInCountryDropdown(country);
        userCartCheckoutPage.enterToTextboxByName("address_1", address1);
        userCartCheckoutPage.enterToTextboxByName("city", city);
        userCartCheckoutPage.enterToTextboxByName("postcode", postCode);
        userCartCheckoutPage.chooseItemInRegionDropdown(region);
        userCartCheckoutPage.clickToContinueButton();

        verifyEquals(userCartCheckoutPage.getSuccessMessageText(userDriver),"Success: You have changed shipping address!");
        userCartCheckoutPage.waitMessageAlertDisappeared(userDriver);
    }

    @Test
    public void Order_02_OrderSuccess() {
        userCartCheckoutPage.clickToChooseShippingMethod();
        double shippingRate = userCartCheckoutPage.getShippingRate();
        String shippingMehthod = userCartCheckoutPage.getShippingMethodTextInPopup();
        userCartCheckoutPage.clickToContinueButtonInShippingMethodPopup();

        verifyEquals(userCartCheckoutPage.getSuccessMessageText(userDriver), "Success: You have changed shipping method!");
        userCartCheckoutPage.waitMessageAlertDisappeared(userDriver);
        verifyEquals(userCartCheckoutPage.getShippingMethodText("value"), shippingMehthod);
        verifyTrue(userCartCheckoutPage.getFlatShippingRate() == shippingRate);

        userCartCheckoutPage.clickToChoosePaymentMethod();
        verifyEquals(userCartCheckoutPage.getPaymentMethodTextInPopup(), "Cash On Delivery");
        userCartCheckoutPage.clickToContinueButtonInPaymentMethodPopup();
        verifyEquals(userCartCheckoutPage.getSuccessMessageText(userDriver), "Success: You have changed payment method!");
        userCartCheckoutPage.waitMessageAlertDisappeared(userDriver);
        verifyEquals(userCartCheckoutPage.getPaymentMethodText("value"), "Cash On Delivery");

        userCartCheckoutPage.clickToConfirmOrderButton();
        verifyEquals(userCartCheckoutPage.getOrderSuccessContent(), "Your order has been placed!");

        userCartCheckoutPage.sleepInSecond(2);
        verifyEquals(userCartCheckoutPage.getTextInCartButton(userDriver), "0 item(s) - $0.00");
    }

    @Test
    public void Order_03_OrderHistory() {
        userOrderHistoryPage = userCartCheckoutPage.chooseItemInMyAccountDropdown(userDriver,"Order History");
        int orderNumber = userOrderHistoryPage.getOrderNumber("Order ID");
        System.out.println("orderNumber: " + orderNumber);
        verifyEquals(userOrderHistoryPage.getOrderStatus("Status"), "Pending");
    }

//    @AfterClass
//    public void afterClass() {
//        closeBrowserDriver();
//    }

    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private UserCartCheckoutPO userCartCheckoutPage;
    private AdminLoginPO adminLoginPage;
    private UserProductDetailPO userProductDetailPage;
    private UserProductPO userProductPage;
    private UserOrderHistoryPO userOrderHistoryPage;
}
