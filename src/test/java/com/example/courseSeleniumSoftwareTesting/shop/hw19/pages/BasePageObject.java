package com.example.courseSeleniumSoftwareTesting.shop.hw19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePageObject {
    WebDriver driver;
    WebDriverWait wait;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10/*seconds*/);
    }
//
//    protected void findAndClick(By locator) {
//        find(locator).click();
//    }
//    protected void findAndClick(WebElement element) {
//       // driver.findElement(element).click();
//    }
//

    protected void findAndClick(WebElement element) {
        element.click();
    }

    protected void find(WebElement element) {
        element.getText();
    }

    protected WebElement findByLocator(By locator) {
       return driver.findElement(locator);
    }

    protected void findAndClickByLocator(By locator) {
        driver.findElement(locator).click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

}
