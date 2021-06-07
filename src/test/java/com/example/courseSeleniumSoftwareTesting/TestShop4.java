package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;





/*Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
    1) открыть главную страницу
    2) открыть первый товар из списка
    3) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
    4) подождать, пока счётчик товаров в корзине обновится
    5) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности
       в корзине было 3 единицы товара
    6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
    7) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
*/

public class TestShop4 extends BaseTestShopWithNewURL {

    private static final By FIRST_PRODUCT_IN_CAMPAIGNS = By.xpath("(//div[@id='box-most-popular']//a[1][@class='link'])[1]");
    private static final By ADD_TO_CART = By.xpath("//button[@name='add_cart_product']");
    private static final By COUNT = By.xpath("//span[@class='quantity']");
    private static final By TITLE = By.xpath("//div[@id='logotype-wrapper']");
    private static final By CHECK_OUT = By.xpath("(//a[@href='https://litecart.stqa.ru/en/checkout'])[3]");


    @Test
    public void shouldAddAndDeleteProductInBasket() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
        addDucks(wait);
        deleteDucks(wait);
    }

    private void addDucks(WebDriverWait wait) {
        for (int i = 1; i <= 3; i++) {
            findAndClick(FIRST_PRODUCT_IN_CAMPAIGNS);
//            if (isElementPresent(By.xpath("//select[@name='options[Size]']"))) {
//                new Select(driver.findElement(By.cssSelector("select[name='options[Size]']"))).selectByVisibleText("Small");
//            }
            find(ADD_TO_CART).click();
            int finalI = i;
            wait.until((WebDriver d) -> d.findElement(COUNT).getText().equals(String.valueOf(finalI)));
            findAndClick(TITLE);
        }
    }

    private void deleteDucks(WebDriverWait wait) {
        findAndClick(CHECK_OUT);
        if (isElementPresent(By.xpath("(//li[@class='shortcut'])[1]"))) {
            findAndClick(By.xpath("//a[@href='#']"));
        }
        findAndClick(By.xpath("(//button[@name='remove_cart_item'])[1]"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@name='remove_cart_item'])[1]")));
        while (driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='remove_cart_item']")));
            findAndClick(By.xpath("(//button[@name='remove_cart_item'])[1]"));
            wait.until(ExpectedConditions.stalenessOf(find(By.cssSelector("table.dataTable"))));
        }
    }

    private void findAndClick(By by) {
        find(by).click();
    }
}
