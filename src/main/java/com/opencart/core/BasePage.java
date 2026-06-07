package com.opencart.core;

import com.opencart.PageUIs.BasePageUI;
import com.opencart.pageObjects.PageGenerator;
import com.opencart.pageObjects.user.UserCartCheckoutPO;
import com.opencart.pageObjects.user.UserShoppingCartPO;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private int SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;
    private int LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;
    public static BasePage getInstance() {
        return new BasePage();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toLowerCase().startsWith("id")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toLowerCase().startsWith("class")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toLowerCase().startsWith("name")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toLowerCase().startsWith("tagname")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toLowerCase().startsWith("css")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toLowerCase().startsWith("xpath")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!!!");
        }
        return by;
    }

    private String castParameter(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    private WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... restParameter) {
        return driver.findElements(getByLocator(castParameter(locator, restParameter)));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter) {
        getWebElement(driver, castParameter(locator, restParameter)).click();
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... restParameter) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, castParameter(locator, restParameter)));
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend, String... restParameter) {
        getWebElement(driver, castParameter(locator, restParameter)).clear();
        getWebElement(driver, castParameter(locator, restParameter)).sendKeys(keyToSend);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).getText();
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getSize();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        sleepInSecond(1);
        clickToElement(driver, parentLocator);

        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem, String... restParameter) {
        sleepInSecond(1);
        clickToElement(driver, castParameter(parentLocator, restParameter));

        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator, String... restParameter) {
        if (!isElementSelected(driver, castParameter(locator, restParameter))) {
            clickToElement(driver, castParameter(locator, restParameter));
        }
    }

    public void uncheckToCheckboxRadio(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    public void uncheckToCheckboxRadio(WebDriver driver, String locator, String... restParameter) {
        if (isElementDisplayed(driver, castParameter(locator, restParameter))) {
            clickToElement(driver, castParameter(locator, restParameter));
        }
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator, String... restParameter) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, castParameter(locator, restParameter)));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator, String... restParameter) {
        new Actions(driver).moveToElement(getWebElement(driver, castParameter(locator, restParameter))).perform();
    }

    public void actionClick(WebDriver driver, String locator, String... restParameter) {
        new Actions(driver).click(getWebElement(driver, castParameter(locator, restParameter))).perform();
    }

    public void sleepInSecond(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... restParameter) {

        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restParameter))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .elementToBeSelected(getByLocator(castParameter(locator, restParameter))));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        // lấy ra đường dẫn của thư mục upload file
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName += filePath + file + "\n";
        }

        // cắt kí tự xuống dòng (\n) đầu cuối
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
    }

    public void clickToCartButton(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.CART_BUTTON);
        clickToElement(driver, BasePageUI.CART_BUTTON);
        sleepInSecond(2);
    }

    public UserShoppingCartPO clickToViewCart(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.VIEW_CART_BUTTON_IN_CART_DROPDOWN);
        clickToElement(driver, BasePageUI.VIEW_CART_BUTTON_IN_CART_DROPDOWN);
        return PageGenerator.getPage(UserShoppingCartPO.class, driver);
    }

    public UserCartCheckoutPO clickToCheckoutButton(WebDriver driver) {
//        waitElementClickable(driver, BasePageUI.CHECKOUT_BUTTON);
        clickToElementByJS(driver, BasePageUI.CHECKOUT_BUTTON);
        return PageGenerator.getPage(UserCartCheckoutPO.class, driver);
    }

    public String getSuccessMessageText(WebDriver driver) {
        waitElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_TEXT);
        return getElementText(driver, BasePageUI.SUCCESS_MESSAGE_TEXT);
    }
}
