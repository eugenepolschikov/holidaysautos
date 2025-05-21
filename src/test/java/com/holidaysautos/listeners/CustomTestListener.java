package com.holidaysautos.listeners;


import com.holidaysautos.core.Screen;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

public class CustomTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Object currentClass = result.getInstance();
            if (currentClass instanceof Screen) {
                WebDriver driver = ((Screen) currentClass).getDriver();
                byte[] srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                saveScreenshot(srcFile);
            }
        } catch (Exception e) {
            Error e1 = new Error(e.getMessage());
            e1.setStackTrace(e.getStackTrace());
            throw e1;
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        Object currentClass = result.getInstance();
        if (currentClass instanceof Screen) {
            WebDriver driver = ((Screen) currentClass).getDriver();
            byte[] srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveScreenshot(srcFile);
        }
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

}