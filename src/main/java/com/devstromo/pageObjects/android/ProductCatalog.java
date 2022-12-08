package com.devstromo.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.devstromo.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalog extends AndroidActions {
    private final AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    public List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    public ProductCatalog(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public ProductCatalog addItemToCartByIndex(int index) {
        addToCart.get(0)
            .click();
        return this;
    }

    public CartPage goToCartPage() throws InterruptedException {
        cart.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }
}
