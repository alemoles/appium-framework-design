package com.devstromo;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.devstromo.pageObjects.android.CartPage;
import com.devstromo.pageObjects.android.ProductCatalog;

public class EcommerceTestTC1 extends BaseTest {

    @Test(description = "Test Case implementing Page Object Model", dataProvider = "getData")
    public void fillFormPOMTCTest(String name, String gender, String country) throws InterruptedException {
        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.setCountrySelection(country);
        ProductCatalog productCatalog = formPage.submitForm();
        CartPage cartPage = productCatalog.addItemToCartByIndex(0)
            .goToCartPage();
        double sum = cartPage.getProductSum();
        double totalAmount = cartPage.getTotalAmountDisplayed();
        assertEquals(sum, totalAmount);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] { { "Ale M", "female", "Argentina" } };
    }
}
