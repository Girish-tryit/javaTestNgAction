package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @BeforeMethod
    public void setUp() {
        WebDriver driver = new DriverFactory().getDriver();
        this.driver.set(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        String testName = result.getMethod().getMethodName();
        File file = new File("target" + File.separator +"screenshots"+ File.separator + testName + ".png");
        takeScreenshot(file);
        driver.get().quit();
    }

    public void takeScreenshot(File destFile) throws IOException {
        // Take screenshot
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        // Save screenshot
        FileUtils.copyFile(file, destFile);
        InputStream is = FileUtils.openInputStream(destFile);
        Allure.addAttachment("Screenshot", is);
    }
}
