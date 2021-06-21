package com.example.courseSeleniumSoftwareTesting.shop.hw19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainPageObject extends BasePageObject{

    @FindBy(xpath = "(//div[@id='box-most-popular']//a[1][@class='link'])[1]")
    private static By FIRST_PRODUCT_IN_CAMPAIGNS;

    @FindBy(xpath = "//div[@id='logotype-wrapper']")
    private static By TITLE;

    @FindBy(xpath = "(//a[@href='https://litecart.stqa.ru/en/checkout'])[3]")
    private static By CHECKOUT;

    public MainPageObject(WebDriver driver) {
        super(driver);
    }

 //   public findAndClickFirstProduct()
}
