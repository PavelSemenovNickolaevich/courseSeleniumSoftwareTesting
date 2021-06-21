package com.example.courseSeleniumSoftwareTesting.shop.hw19.base;

import com.example.courseSeleniumSoftwareTesting.shop.hw19.applictions.Application;
import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.CustomerDetailsPage;
import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.DuckPageObject;
import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.MainPageObject;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    public Application app;
    public CustomerDetailsPage customerDetailsPage;
    public DuckPageObject duckPageObject;
    public MainPageObject mainPageObject;
    private String browser;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        BrowserDriverFactory browserDriverFactory = new BrowserDriverFactory(browser);
        driver = browserDriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.get("https://litecart.stqa.ru/en/");
        app = new Application(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

//    protected WebElement find(By locator) {
//        return driver.findElement(locator);
//    }
}
