package com.example.courseSeleniumSoftwareTesting.shop.hw19.tests;

import com.example.courseSeleniumSoftwareTesting.shop.hw19.base.BaseTest;
import org.testng.annotations.Test;

public class AddAndDeleteTests extends BaseTest {

    @Test
    public void shouldAddAndDeleteProductInBasket() throws InterruptedException {
        app.duckPageObject().addDucks();
        app.customerDetailsPage().deleteDucks();
    }
}
