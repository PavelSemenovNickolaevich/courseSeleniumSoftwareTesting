package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class BaseTest {
    //private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebDriver driver;
    private String browser;

    @BeforeMethod
    public void setUp(@Optional("edge") String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            // driver.set(new ChromeDriver());
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            //  driver.set(new FirefoxDriver());
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
        }
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("unexpectedAlertBehaviour", "dismiss");
//        driver = new ChromeDriver(cap);
//        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/login.php");
    }


    @Test
    public void shouldOpenAdminPage() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
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
}
