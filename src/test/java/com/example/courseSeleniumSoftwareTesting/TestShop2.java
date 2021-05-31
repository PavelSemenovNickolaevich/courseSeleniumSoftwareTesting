package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;


//Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.
//
//        Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:
//
//        а) на главной странице и на странице товара совпадает текст названия товара
//        б) на главной странице и на странице товара совпадают цены (обычная и акционная)
//        в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa
//        представлении одинаковые значения для каналов R, G и B)
//        г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении
//        каналы G и B имеют нулевые значения)
//        (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
//        д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)

public class TestShop2 extends BaseTestShop {

    private static final By FIRST_PRODUCT_IN_CAMPAIGNS = By.xpath("(//div[@id='box-campaigns']//a[1][@class='link'])[1]");

    @Test
    public void shouldOpenCorrectPage() {
        String mainPageNameOfProduct = find(By.xpath("(//div[@id='box-campaigns']//div[@class='name'])[1]")).getText();
        String mainPageRegularPrice = find(By.xpath("(//div[@id='box-campaigns']//s[@class='regular-price'])[1]")).getText();
        String mainPageRegularPriceColor = find(By.xpath("(//div[@id='box-campaigns']//s[@class='regular-price'])[1]")).getCssValue("color");
        String mainPageDiscountPrice = find(By.xpath("(//div[@id='box-campaigns']//strong[@class='campaign-price'])[1]")).getText();
        String mainPageDiscountColor = find(By.xpath("(//div[@id='box-campaigns']//strong[@class='campaign-price'])[1]")).getCssValue("color");
        Dimension mainPageRegularPriceSize = find(By.xpath("(//div[@id='box-campaigns']//s[@class='regular-price'])[1]")).getSize();
        Dimension mainPageDiscountPriceSize = find(By.xpath("(//div[@id='box-campaigns']//strong[@class='campaign-price'])[1]")).getSize();

        String hexColor = Color.fromString(mainPageDiscountColor).asHex();
        Assert.assertEquals("#cc0000", hexColor);
        Assert.assertEquals("#777777", Color.fromString(mainPageRegularPriceColor).asHex());
        Assert.assertTrue(mainPageDiscountPriceSize.height > mainPageRegularPriceSize.height);
        Assert.assertTrue(mainPageDiscountPriceSize.width > mainPageRegularPriceSize.width);

        driver.findElement(FIRST_PRODUCT_IN_CAMPAIGNS).click();

        String nameOfProduct = find(By.xpath("//h1[@itemprop='name']")).getText();
        String regularPriceOfProduct = find(By.xpath("//s[@class='regular-price']")).getText();
        String regularPriceOfProductColor = find(By.xpath("//s[@class='regular-price']")).getCssValue("color");
        String discountPriceOfProduct = find(By.xpath("//strong[@class='campaign-price']")).getText();
        String discountPriceOfProductColor = find(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");
        Dimension regularPriceOfProductSize = find(By.xpath("//s[@class='regular-price']")).getSize();
        Dimension discountPriceOfProductSize = find(By.xpath("//strong[@class='campaign-price']")).getSize();

        Assert.assertEquals("#666666", Color.fromString(regularPriceOfProductColor).asHex());
        Assert.assertEquals("#cc0000", Color.fromString(discountPriceOfProductColor).asHex());
        Assert.assertTrue(discountPriceOfProductSize.height > regularPriceOfProductSize.height);
        Assert.assertTrue(discountPriceOfProductSize.width > regularPriceOfProductSize.width);
        Assert.assertEquals(mainPageNameOfProduct, nameOfProduct);
        Assert.assertEquals(mainPageRegularPrice, regularPriceOfProduct);
        Assert.assertEquals(mainPageDiscountPrice, discountPriceOfProduct);
    }

    private WebElement find(By locator) {
        return driver.findElement(locator);
    }
}
