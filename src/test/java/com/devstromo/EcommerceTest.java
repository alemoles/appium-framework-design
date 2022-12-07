package com.devstromo;

import static io.appium.java_client.android.nativekey.AndroidKey.BACK;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.AssertJUnit.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.devstromo.pageObjects.android.FormPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EcommerceTest extends BaseTest {

    @Test(description = "Test Case in Filling the form details for shopping")
    public void fillFormTCTest() {
        FormPage page = new FormPage(driver);
        page.setNameField("Ale M");
        page.setGender("female");
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
    }

    @Test(description = "Verifying toast messages for error validations")
    public void verifyToastMessagesTCTest() throws InterruptedException {

        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        String toastErrorMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]"))
            .getAttribute("name");
        assertEquals("Please enter your name", toastErrorMessage);
    }

    @Test(description = "Test Case - Scrolling in product list example with Appium Android scroll")
    public void scrollingTCTest() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Ale M");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
            .size();
        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
                .get(i)
                .getText();
            if (productName.equals("Jordan 6 Rings")) {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart"))
                    .get(i)
                    .click();
            }
        }
        String cartCount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/counterText"))
            .getText();
        assertEquals("1", cartCount);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
            .click();
    }

    @Test(description = "Testcase-Dynamically selecting Product by scanning list based on text")
    public void selectProductDynamicallyTCTest() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Ale M");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
            .size();
        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
                .get(i)
                .getText();
            if (productName.equals("Jordan 6 Rings")) {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart"))
                    .get(i)
                    .click();
            }
        }
        String cartCount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/counterText"))
            .getText();
        assertEquals("1", cartCount);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
            .click();

        // avoid conflicting id com.androidsample.generalstore:id/productName
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        String lastPageProduct = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName"))
            .getText();
        assertEquals("Jordan 6 Rings", lastPageProduct);
    }

    @Test(description = "TestCase - Validating Total amount generated functionality")
    public void validateTotalAmountTCTest() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Ale M");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
            .get(0)
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
            .click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double sum = 0;
        for (WebElement productPrice : productPrices) {
            String amountString = productPrice.getText();
            double price = getFormattedAmount(amountString);
            sum += price;
        }
        assertEquals(160.97, sum);
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
            .getText();
        double displayFormattedSum = getFormattedAmount(displaySum);
        assertEquals(displayFormattedSum, sum);
    }

    @Test(description = "TestCase - Validating Mobile Gestures of App ( Tap, Long Press)")
    public void validateGesturesTCTest() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Ale M");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
            .get(0)
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
            .click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double sum = 0;
        for (WebElement productPrice : productPrices) {
            String amountString = productPrice.getText();
            double price = getFormattedAmount(amountString);
            sum += price;
        }
        assertEquals(160.97, sum);
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
            .getText();
        double displayFormattedSum = getFormattedAmount(displaySum);
        assertEquals(displayFormattedSum, sum);
        WebElement termsButton = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(termsButton);
        driver.findElement(AppiumBy.id("android:id/button1"))
            .click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed"))
            .click();
    }

    //TestCase - Automate Hybrid App with context Switch using Chrome Driver
    @Test(description = "TestCase - Handling Web View")
    public void handleWebViewTCTest() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Ale M");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"))
            .click();
        driver.findElement(AppiumBy.id("android:id/text1"))
            .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
            .get(0)
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
            .click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double sum = 0;
        for (WebElement productPrice : productPrices) {
            String amountString = productPrice.getText();
            double price = getFormattedAmount(amountString);
            sum += price;
        }
        assertEquals(160.97, sum);
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
            .getText();
        double displayFormattedSum = getFormattedAmount(displaySum);
        assertEquals(displayFormattedSum, sum);
        WebElement termsButton = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(termsButton);
        driver.findElement(AppiumBy.id("android:id/button1"))
            .click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox"))
            .click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed"))
            .click();

        Thread.sleep(10000);
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println(context);
        }
        // continue automation in web view
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(AppiumBy.name("q"))
            .sendKeys("raul shetty academy");
        driver.findElement(AppiumBy.name("q"))
            .sendKeys(ENTER);
        Thread.sleep(5000);
        driver.pressKey(new KeyEvent(BACK));
        // SWITCH TO NATIVE
        driver.context("NATIVE_APP");
    }
}
