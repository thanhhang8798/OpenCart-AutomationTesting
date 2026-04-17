package com.opencart.pageObjects.admin;

import com.opencart.PageUIs.admin.AdminLoginPUI;
import com.opencart.core.BasePage;
import com.opencart.pageObjects.PageGenerator;
import org.openqa.selenium.WebDriver;

public class AdminLoginPO extends BasePage {
    private WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUserNameTextbox(String userName) {
        waitElementVisible(driver, AdminLoginPUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPUI.USERNAME_TEXTBOX, userName);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, AdminLoginPUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPUI.PASSWORD_TEXTBOX, password);
    }

    public Object getDismissibleAllertText() {
        waitElementVisible(driver, AdminLoginPUI.DISMISSABLE_ALERT);
        return getElementText(driver, AdminLoginPUI.DISMISSABLE_ALERT);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitElementClickable(driver, AdminLoginPUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPO.class, driver);
    }
}
