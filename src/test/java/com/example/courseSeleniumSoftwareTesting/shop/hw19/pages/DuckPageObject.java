package com.example.courseSeleniumSoftwareTesting.shop.hw19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuckPageObject extends BasePageObject {

    public DuckPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final By COUNT = By.xpath("//span[@class='quantity']");

    @FindBy(xpath = "//button[@name='add_cart_product']")
    private static WebElement ADD_TO_CART_BTN;

    @FindBy(xpath = "(//div[@id='box-most-popular']//a[1][@class='link'])[1]")
    private static WebElement FIRST_PRODUCT_IN_CAMPAIGNS;

    @FindBy(xpath = "//div[@id='logotype-wrapper']")
    private static WebElement TITLE;


    public void addDucks() {
        for (int i = 1; i <= 3; i++) {
            findAndClick(FIRST_PRODUCT_IN_CAMPAIGNS);
            findAndClick(ADD_TO_CART_BTN);
            int finalI = i;
            wait.until((WebDriver d) -> d.findElement(COUNT).getText().equals(String.valueOf(finalI)));
            findAndClick(TITLE);
        }
    }



}
