package com.jum.common;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import org.testng.TestListenerAdapter;

public class TestFailListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        BaseTester baseTester = (BaseTester) result.getInstance();
        AndroidDriver<WebElement> driver = baseTester.getDriver();
        takePhoto(driver);
        logCaseStep(result);
        exceptedResult(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        BaseTester baseTester = (BaseTester) result.getInstance();
        AndroidDriver<WebElement> driver = baseTester.getDriver();
        takePhoto(driver);
        logCaseStep(result);
        exceptedResult(result);
    }

    @Attachment(value = "失败截图",type = "image/png")
    public byte[]  takePhoto(AndroidDriver driver){
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }

    /**
     * 打印测试步骤
     * @param result
     */
    @Attachment(value = "操作步骤如下：")
    public String logCaseStep(ITestResult result){
        String step = "a. xxx  ||  b. yyy";
        return step;
    }

    /**
     * 打印测试步骤
     * @param result
     */
    @Attachment(value = "期望结果如下：")
    public String exceptedResult(ITestResult result){
        String res = "显示查询结果";
        return res;
    }
}