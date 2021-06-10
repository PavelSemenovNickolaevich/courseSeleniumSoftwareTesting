package com.example.courseSeleniumSoftwareTesting.admin;

import com.example.courseSeleniumSoftwareTesting.BaseTestAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class TestAdmin4 extends BaseTestAdmin {

    @Test
    public void shouldCheckLinkInNewTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 40/*seconds*/);
        Thread.sleep(1000);
        WebElement element = wait.until(d -> d.findElement(By.xpath("//span[contains(text(),'Countries')]")));
        element.click();
        find(By.xpath("(//i[@class=\"fa fa-pencil\"])[1]")).click();
        String mainWindow = driver.getWindowHandle();
        List<WebElement> list = driver.findElements(By.xpath("//i[@class=\"fa fa-external-link\"]"));
        for (WebElement webElement : list) {
            webElement.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = driver.getWindowHandles();
            windows.remove(mainWindow);
            String newWindow = windows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
}
