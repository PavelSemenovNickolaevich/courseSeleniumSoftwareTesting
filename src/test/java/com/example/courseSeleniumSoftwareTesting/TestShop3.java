package com.example.courseSeleniumSoftwareTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


//Сценарий должен состоять из следующих частей:
//
//        1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало
//        с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
//        2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//        3) повторный вход в только что созданную учётную запись,
//        4) и ещё раз выход.
//
//        В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.
//
//        Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
//
//        Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки. Если сценарий
//        дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.
//
//        В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.

public class TestShop3 extends BaseTestShopWithNewURL {
    private static final By CREATE_NEW_CUSTOMER_LOCATOR = By.xpath("//a[contains(text(),'New customers click here')]");
    private static long time = System.currentTimeMillis();
    private final String email = time + "skynet@gmail.col";
    private final String password = "+12345";

    @Test(testName = "Create new User and Login")
    public void shouldRegistryClient() throws InterruptedException {
        createNewUser();
        login();
    }

    private void createNewUser() throws InterruptedException {
        find(CREATE_NEW_CUSTOMER_LOCATOR).click();
        Thread.sleep(1000);
        find(By.xpath("//input[@name='firstname']")).sendKeys("John");
        find(By.xpath("//input[@name='lastname']")).sendKeys("Connor");
        find(By.xpath("//input[@name='address1']")).sendKeys("123 st");
        find(By.xpath("//input[@name='postcode']")).sendKeys("12345");
        find(By.xpath("//input[@name='city']")).sendKeys("LA");
        find(By.xpath("//span[@class='select2-selection__rendered']")).click();
        find(By.xpath("//input[@type='search']")).sendKeys("United States" + Keys.ENTER);
        find(By.xpath("//input[@name='email']")).sendKeys(email);
        find(By.xpath("//input[@name='phone']")).sendKeys(password);
        find(By.xpath("//input[@name='password']")).sendKeys(password);
        find(By.xpath("//input[@name='confirmed_password']")).sendKeys("12345");
        new Select(driver.findElement(By.cssSelector("select[name='zone_code']"))).selectByVisibleText("California");
        find(By.xpath("//button[@type='submit']")).click();
        find(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    private void login() {
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='login']")).click();
    }
}
