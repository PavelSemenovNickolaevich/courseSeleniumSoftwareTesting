package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

      /*  Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
        Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
        Сценарий должен проверять, что у каждого товара имеется ровно один стикер. */


public class TestShop1 extends BaseTestShop {

    @Test
    public void shouldCheckStickersAreAvailable() {

        List<WebElement> ducks = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']"));
        for (WebElement duck : ducks) {
            List<WebElement> stickers = duck.findElements(By.xpath(".//div[@class='image-wrapper']/div[@title]"));
            if (stickers.size() != 1) {
                Assert.fail("Sticker more then 1");
            }
        }
    }
}