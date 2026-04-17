package com.opencart.browserFactory;

import org.openqa.selenium.WebDriver;

public interface BrowserFactory {
    public abstract WebDriver getDriver();
}
