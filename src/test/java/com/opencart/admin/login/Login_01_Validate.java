package com.opencart.admin.login;
import com.opencart.core.BaseTest;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.admin.AdminDashboardPO;
import com.opencart.pageObjects.admin.AdminLoginPO;
import com.opencart.utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_01_Validate extends BaseTest {
    private WebDriver adminDriver;
    private PropertiesConfig propertiesConfig;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browser) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        adminDriver = getBrowserDriver(propertiesConfig.getApplicationAdminUrl(), browser);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);
    }

    @Test
    public void Login_01_IncorrectUsername() {
        adminLoginPage.enterToUserNameTextbox("kkk");
        adminLoginPage.enterToPasswordTextbox("Auto222@@@");
        adminLoginPage.clickToLoginButton();
        verifyEquals(adminLoginPage.getDismissibleAllertText(), "No match for Username and/or Password.");
    }

    @Test
    public void Login_02_IncorectPassword() {
        adminLoginPage.enterToUserNameTextbox("automationfc");
        adminLoginPage.enterToPasswordTextbox("Auto222@@@1");
        adminLoginPage.clickToLoginButton();
        verifyEquals(adminLoginPage.getDismissibleAllertText(), "No match for Username and/or Password.");
    }

    @Test
    public void Login_03_BlankUsernameOrPassword() {
        adminLoginPage.enterToUserNameTextbox("");
        adminLoginPage.enterToPasswordTextbox("Auto222@@@");
        adminLoginPage.clickToLoginButton();
        verifyEquals(adminLoginPage.getDismissibleAllertText(), "No match for Username and/or Password.");
    }

    @Test
    public void Login_04_CorrectUsernameAndPassword() {
        adminLoginPage.enterToUserNameTextbox("automationfc");
        adminLoginPage.enterToPasswordTextbox("Auto222@@@");
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        verifyTrue(adminDashboardPage.isDashboardTitleDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver(adminDriver);
    }

    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;

}
