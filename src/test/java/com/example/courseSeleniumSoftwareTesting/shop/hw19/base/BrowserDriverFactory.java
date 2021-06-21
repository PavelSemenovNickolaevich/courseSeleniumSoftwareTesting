package com.example.courseSeleniumSoftwareTesting.shop.hw19.base;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //    private WebDriver driver;
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        DesiredCapabilities cap = new DesiredCapabilities();
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                ChromeOptions optionsChrome = new ChromeOptions().merge(cap);
                //   driver = new ChromeDriver(optionsChrome);
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                FirefoxOptions optionsFire = new FirefoxOptions().setBinary("C:/Program Files/Firefox Nightly/firefox.exe");
                //  driver = new FirefoxDriver(optionsFire);
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                //  driver = new EdgeDriver();
                driver.set(new EdgeDriver());
                break;
        }
        return driver.get();
    }
}
