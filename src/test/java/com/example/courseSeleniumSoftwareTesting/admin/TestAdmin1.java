package com.example.courseSeleniumSoftwareTesting.admin;

import com.example.courseSeleniumSoftwareTesting.BaseTestAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

   /*   1) входит в панель администратора http://localhost/litecart/admin
        2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
        3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)

        Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл. */



public class TestAdmin1 extends BaseTestAdmin {

    @Test
    public void shouldOpenCheckSection() throws InterruptedException {
        WebElement elementBox = driver.findElement(By.xpath("//ul[@id='box-apps-menu']"));
        List<WebElement> elements = elementBox.findElements(By.xpath("//li[@id='app-']"));
        Thread.sleep(1000);
        int numberOfListElements = elements.size();
        for (int i = 0; i < numberOfListElements; i++) {
            elements = driver.findElements(By.xpath("//li[@id='app-']"));
            elements.get(i).click();
            int docCounts = driver.findElements(By.xpath("//ul[@class='docs']//li[@id]")).size();
            if (docCounts > 0) {
                for (int j = 0; j < docCounts; j++) {
                    List<WebElement> element = driver.findElements(By.xpath("//ul[@class='docs']/li[@id]"));
                    Thread.sleep(2000);
                    element.get(j).click();
                    isElementPresent(By.xpath("//h1[@style='margin-top: 0px;']"));
                }
            }
            isElementPresent(By.xpath("//h1[@style='margin-top: 0px;']"));
        }
    }
}
