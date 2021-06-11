package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import java.util.concurrent.TimeUnit;

public class BaseTestShop {

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/en/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
}

