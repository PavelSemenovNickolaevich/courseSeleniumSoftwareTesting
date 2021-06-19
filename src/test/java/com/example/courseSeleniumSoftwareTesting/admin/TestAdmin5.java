package com.example.courseSeleniumSoftwareTesting.admin;


//Задание 17. Проверьте отсутствие сообщений в логе браузера
//        Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в
//        учебном приложении, а именно -- страниц товаров в каталоге в административной панели.
//
//        Сценарий должен состоять из следующих частей:
//
//        1) зайти в админку
//        2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
//        3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)

import com.example.courseSeleniumSoftwareTesting.BaseTestAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestAdmin5 extends BaseTestAdmin {

    @Test
    public void shouldLogsAreAbsence() throws InterruptedException {
        driver.get("http://localhost/litecart/litecart-1.3.7/public_html/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> list = driver.findElements(By.cssSelector("a[href*='product_id']"));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).click();
            Thread.sleep(3000);
            find(By.xpath("//button[@name='cancel']")).click();
            i++;
            Thread.sleep(3000);
            list = driver.findElements(By.cssSelector("a[href*='product_id']"));
        }


        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
            Assert.assertTrue(l.equals(""));
        }


    }
}
