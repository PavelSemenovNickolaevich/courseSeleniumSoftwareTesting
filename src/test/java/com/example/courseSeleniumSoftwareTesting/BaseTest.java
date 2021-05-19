package com.example.courseSeleniumSoftwareTesting;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/login.php");
    }

    @Test
    public void shouldOpenAdminPage() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        String url = "http://localhost/litecart/litecart-1.3.7/public_html/admin/";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
