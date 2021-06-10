package com.example.courseSeleniumSoftwareTesting.admin;

import com.example.courseSeleniumSoftwareTesting.BaseTestAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TestAdmin3 extends BaseTestAdmin {

    private static final File relativePath = new File("src/test/resources/duck.png");
    private static final long time = System.currentTimeMillis();

    @Test
    public void shouldAddProduct() throws InterruptedException {
        Thread.sleep(1000);
        addGeneral();
        Thread.sleep(1000);
        addInformation();
        Thread.sleep(1000);
        addPrices();
        driver.findElement(By.xpath("//button[@name='save']")).click();
        checkProductInCatalog();
    }

    private void checkProductInCatalog() {
        driver.findElement(By.xpath("(//span[contains(text(),'Catalog')])[2]")).click();
        String nameOfProduct = driver.findElement(By.xpath("//a[contains(text(),'Duck Terminator " + time + "')]")).getText();
        Assert.assertEquals("Duck Terminator " + time, nameOfProduct);
    }

    private void addGeneral() throws InterruptedException {
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//li[2]")).click();
        driver.findElement(By.xpath("//a[contains(text(),' Add New Product')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='radio' and @value='1']")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("Duck Terminator " + time);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("007");
        driver.findElement(By.xpath("//input[@value='1-1']")).click();
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("10");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(relativePath.getAbsolutePath());
        Thread.sleep(1000);
        //   driver.switchTo().frame(driver.findElement(By.xpath("//input[@name='date_valid_from']")));
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("10/09/2020");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("04/06/2021");
    }

    private void addPrices() {
        driver.findElement(By.xpath("//a[@href='#tab-prices']")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("300");
        new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")))
                .selectByVisibleText("Euros");
        driver.findElement(By.xpath(" //input[@name='prices[USD]']")).sendKeys("300");
        driver.findElement(By.xpath(" //input[@name='gross_prices[USD]']")).sendKeys("10");
    }

    private void addInformation() {
        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();
        new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']"))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("cinema, duck");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("Description");
    }

    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        ((JavascriptExecutor) driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }
}
