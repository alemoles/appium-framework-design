package com.devstromo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureApp() throws MalformedURLException {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        File file = new File(resourceDirectory + "/General-Store.apk");
        File chromeDriver = new File(resourceDirectory + "/chromedriver.exe");
        String basePath = System.getenv("APPDATA")
            .replace("\\", "//");
        // AndroidDriver
        // Appium code -> Appium Server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Nexus 5 API 24");
        options.setApp(file.getAbsolutePath());

        options.setChromedriverExecutable(chromeDriver.getAbsolutePath());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage()
            .timeouts()
            .implicitlyWait(Duration.ofSeconds(10));

        service = new AppiumServiceBuilder().withAppiumJS(new File(basePath + "//npm//node_modules//appium//build//lib//main.js"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build();
        //start server
        service.start();

    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));

        } while (canScrollMore);
    }

    public double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
            ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        // stop server
        service.stop();
    }
}
