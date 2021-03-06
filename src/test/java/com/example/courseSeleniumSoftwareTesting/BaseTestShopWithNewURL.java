package com.example.courseSeleniumSoftwareTesting;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class BaseTestShopWithNewURL {
    //private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected WebDriver driver;
    private String browser;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("unexpectedAlertBehaviour", "dismiss");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                ChromeOptions optionsChrome = new ChromeOptions().merge(cap);
                driver = new ChromeDriver(optionsChrome);
                break;
            // driver.set(new ChromeDriver());
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                FirefoxOptions optionsFire = new FirefoxOptions().setBinary("C:/Program Files/Firefox Nightly/firefox.exe");
                driver = new FirefoxDriver(optionsFire);
                break;
            //  driver.set(new FirefoxDriver());
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
        }
        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://litecart.stqa.ru/en/");

    }

    @Test(enabled = false)
    public void shouldOpenAdminPage() {
        Set<Cookie> cookies = driver.manage().getCookies();
        cookies.forEach(System.out::println);
        String url = "http://localhost/litecart/litecart-1.3.7/public_html/admin/";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

//    boolean isElementPresent(By locator) {
//        try {
//            driver.findElement(locator);
//            return true;
//        } catch (NoSuchElementException ex) {
//            return false;
//        }
//    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
}
