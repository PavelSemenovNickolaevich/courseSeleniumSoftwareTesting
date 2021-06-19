package com.example.courseSeleniumSoftwareTesting;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.io.Files.copy;
import static org.testng.Assert.assertEquals;

public class BaseTestAdmin {
    //private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected EventFiringWebDriver driver;
    private String browser;
    private WebDriverWait wait;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("scree " + System.currentTimeMillis() +  " png");
            try {
                Files.copy(tempFile, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }

    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("unexpectedAlertBehaviour", "dismiss");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                ChromeOptions optionsChrome = new ChromeOptions().merge(cap);
                driver = new EventFiringWebDriver(new ChromeDriver(optionsChrome));
                driver.register(new MyListener());
                break;
            // driver.set(new ChromeDriver());
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                FirefoxOptions optionsFire = new FirefoxOptions().setBinary("C:/Program Files/Firefox Nightly/firefox.exe");
                driver = new EventFiringWebDriver(new FirefoxDriver(optionsFire));
                break;
            //  driver.set(new FirefoxDriver());
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                driver = new EventFiringWebDriver(new EdgeDriver());
                break;
        }
        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
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

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
}
