package com.ui.automation.framework.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.ui.automation.framework.base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = null;

        if (testClass instanceof BaseTest) {
            driver = ((BaseTest) testClass).getDriver();
        }

        if (driver != null) {
            attachScreenshot(driver);
        }
    }

    private void attachScreenshot(WebDriver driver) {
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        io.qameta.allure.Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new java.io.ByteArrayInputStream(bytes),
                ".png"
        );
    }
}