package com.opencart.browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CoccocBrowserManager implements BrowserFactory {
    @Override
    public WebDriver getDriver() {
        ChromeOptions ccOptions = new ChromeOptions();
        ccOptions.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
        return new ChromeDriver(ccOptions);
    }
}
