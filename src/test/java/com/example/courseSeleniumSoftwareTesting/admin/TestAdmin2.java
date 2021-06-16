package com.example.courseSeleniumSoftwareTesting.admin;


import com.example.courseSeleniumSoftwareTesting.BaseTestAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


/*Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.

        1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
        а) проверить, что страны расположены в алфавитном порядке
        б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить
        , что зоны расположены в алфавитном порядке

        2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
        зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке

        Можно оформить сценарии либо как тесты, либо как отдельные исполняемые файлы. */
public class TestAdmin2 extends BaseTestAdmin {

    private static final By COUNTRIES_LOCATOR = By.xpath("//span[contains(text(),'Countries')]");
    private static final By COUNTRIES_OF_LIST = By.xpath("//form[@name='countries_form']//td[5]//a");
    private static final By ZONES_OF_LIST = By.xpath("//form[@name='countries_form']//td[6]");
    private static final By NAMES_ZONE_OF_LIST = By.xpath("//table[@class='dataTable']//td[3]//input[@type='hidden']");
    private static final By GEOZONES_LOCATOR = By.xpath("//span[contains(text(),'Geo Zones')]");
    private static final By GEOZONES_FROM_GEOZONES_MENU = By.xpath("//table[@class='dataTable']//td[3]//a");


    @Test(testName = "First test with subtask A and B")
    public void shouldSortCountriesAndGeozons() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(COUNTRIES_LOCATOR).click();
        List<WebElement> listCountries = driver.findElements(COUNTRIES_OF_LIST);
        compareAlphabetic(listCountries);
        List<WebElement> listZones = driver.findElements(ZONES_OF_LIST);
        int size = listZones.size();
        for (int i = 0; i < size; i++) {
            if (!listZones.get(i).getText().equals("0")) {
                listCountries.get(i).click();
                List<WebElement> nameZones = driver.findElements(NAMES_ZONE_OF_LIST);
                compareAlphabetic(nameZones);
                Thread.sleep(1000);
                driver.findElement(By.xpath("//button[@name='cancel']")).click();
                i++;
            }
            listCountries = driver.findElements(COUNTRIES_OF_LIST);
            listZones = driver.findElements(ZONES_OF_LIST);
        }
    }

    @Test(testName = "Second test")
    public void shouldAlphabeticGeoZones() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(GEOZONES_LOCATOR).click();
        List<WebElement> elements = driver.findElements(GEOZONES_FROM_GEOZONES_MENU);
        for (int i = 0; i < elements.size(); i++) {
            Thread.sleep(2000);
            elements.get(i).click();
            List<WebElement> el = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]//option[@selected='selected']"));
            compareAlphabetic(el);
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
            elements = driver.findElements(GEOZONES_FROM_GEOZONES_MENU);
        }
    }

    private void compareAlphabetic(List<WebElement> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            if (list.get(j + 1).getText().compareTo(list.get(j).getText()) < 0) {
                Assert.fail("Zones/Countries list in non-alphabetic order!");
            }
        }
    }
}
