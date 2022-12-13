package com.devstromo.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * When you have actions for multiple platforms like Android, IOS, etc
 */
public class AppiumUtils {
    private final AppiumDriver driver;

    public AppiumUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    public void waitForElementAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(element, "text", "Cart"));
    }
}
