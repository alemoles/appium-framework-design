package com.devstromo.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.devstromo.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

    private final AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;
    //    driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public double getProductSum() {
        int count = productList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productList.get(i)
                .getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
        }

        return totalSum;
    }

    public Double getTotalAmountDisplayed() {
        return getFormattedAmount(totalAmount.getText());
    }

    public void acceptTermsConditions() {
        longPressAction(terms);
        acceptButton.click();
    }

    public void submitOrder() {
        checkBox.click();
        proceed.click();
    }
}
