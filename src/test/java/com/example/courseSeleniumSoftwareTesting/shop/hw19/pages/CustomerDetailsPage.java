package com.example.courseSeleniumSoftwareTesting.shop.hw19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CustomerDetailsPage extends BasePageObject {

    public CustomerDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@name='remove_cart_item'])[1]")
    private static WebElement REMOVE_FIRST_ITEM;

    private static final By REMOVE_ITEM = By.xpath("//button[@name='remove_cart_item']");

    @FindBy(xpath = "//td[@class='item']")
    private List<WebElement> elementsOfProducts;

    @FindBy(xpath = "(//a[@href='https://litecart.stqa.ru/en/checkout'])[3]")
    private static WebElement CHECKOUT;


    public void deleteDucks() throws InterruptedException {
        findAndClick(CHECKOUT);
        if (isElementPresent(By.xpath("(//li[@class='shortcut'])[1]"))) {
            Thread.sleep(1000);
            findAndClickByLocator(By.xpath("(//a[@href='#'])[1]"));
        }
        while (driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(REMOVE_ITEM));
            List<WebElement> elements = elementsOfProducts;
            String name = elements.get(0).getText();
            findAndClick(REMOVE_FIRST_ITEM);
            wait.until(ExpectedConditions.stalenessOf(findByLocator(By.xpath("//td[contains(text(),'" + name + "')]"))));
        }
    }
}
