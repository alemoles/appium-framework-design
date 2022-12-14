package com.devstromo;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class EcommerceTestTC2 extends BaseTest {

    @BeforeMethod
    public void preSetup() {
        //screen to home page
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);
    }

    @Test
    public void fillFormErrorValidationTCTest() throws InterruptedException {

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
        String toastErrorMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]"))
            .getAttribute("name");
        assertEquals("Please enter your name", toastErrorMessage);
    }

    @Test
    public void fillFormPositiveFlowTest() throws InterruptedException {
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
        int size = driver.findElements(AppiumBy.xpath("(//android.widget.Toast)[1]"))
            .size();
        assertEquals(0, size);
    }

}
