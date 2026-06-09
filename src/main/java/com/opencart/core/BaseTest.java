package com.opencart.core;

import com.opencart.browserFactory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String url, String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxBrowserManager().getDriver();
                break;
            case CHROME:
                driver = new ChromeBrowserManager().getDriver();
                break;
            case EDGE:
                driver = new EdgeBrowserManager().getDriver();
                break;
            case COCCOC:
                driver = new CoccocBrowserManager().getDriver();
                break;
            default:
                throw new RuntimeException("Browser name is invalid");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }

    protected void closeBrowserDriver(WebDriver driver) {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
//            log.info("OS name = " + osName);
            String driverInstanceName = driver.toString().toLowerCase();
//            log.info("Driver instance name = " + driverInstanceName);
            String browserDriverName = null;
            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else {
                browserDriverName = "safaridriver";
            }
            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
//            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

//    @BeforeSuite
//    public void deleteFileInReport() {
//        // Remove all file in Allure attachment (json file)
//        deleteAllFileInFolder("htmlAllure");
//    }
//
//    public void deleteAllFileInFolder(String folderName) {
//        try {
//            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
//            File file = new File(pathFolderDownload);
//            File[] listOfFiles = file.listFiles();
//            if (listOfFiles.length != 0) {
//                for (int i = 0; i < listOfFiles.length; i++) {
//                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
//                        new File(listOfFiles[i].toString()).delete();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }
//    }
}
