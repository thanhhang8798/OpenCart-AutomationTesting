package com.opencart.pageObjects.user;

import com.opencart.PageUIs.user.UserLoginPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailAddressTextbox(String userEmailAddress) {
        waitElementVisible(driver, UserLoginPUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver, UserLoginPUI.EMAIL_ADDRESS_TEXTBOX, userEmailAddress);
    }

    public void enterToEmailPasswordTextbox(String userPassword) {
        waitElementVisible(driver, UserLoginPUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPUI.PASSWORD_TEXTBOX, userPassword);
    }

    public UserMyAccountPO clickToLoginButton() {
        waitElementClickable(driver, UserLoginPUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserMyAccountPO.class, driver);
    }
}
