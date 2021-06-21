package com.example.courseSeleniumSoftwareTesting.shop.hw19.applictions;

import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.CustomerDetailsPage;
import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.DuckPageObject;
import com.example.courseSeleniumSoftwareTesting.shop.hw19.pages.MainPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {

    private WebDriver driver;
    private Application app;
    private WebDriverWait wait;

    private DuckPageObject duckPageObject;
    private MainPageObject mainPageObject;
    private CustomerDetailsPage customerDetailsPage;

    public Application(WebDriver driver) {
        this.driver = driver;
        duckPageObject = new DuckPageObject(driver);
        mainPageObject = new MainPageObject(driver);
        customerDetailsPage = new CustomerDetailsPage(driver);
    }



    public DuckPageObject duckPageObject() {
        return duckPageObject;
    }

    public MainPageObject mainPageObject() {
        return mainPageObject;
    }

    public CustomerDetailsPage customerDetailsPage() {
        return customerDetailsPage;
    }

}
