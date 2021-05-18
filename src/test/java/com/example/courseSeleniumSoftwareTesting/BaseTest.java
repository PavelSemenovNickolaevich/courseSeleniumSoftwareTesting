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
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void shouldOpenPage() {
        driver.get("https://www.google.com/");
        String googleUrl = "https://www.google.com/";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(googleUrl, currentUrl);
    }
}
