package com.jum.common;

import com.jum.page.MallPage;
import com.jum.util.CmdUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 基础操作
 */
@Slf4j
public class CommonStep extends BaseTester{
    public static String findBtn = "发现";
    public static String miniApp = "小程序";
    public static String appName = "IN商圈";
    public static String inputethod = "com.sohu.inputmethod.sogou/.SogouIME";

    public WebElement waitElementByXpath(long wait, String location) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        WebElement e = webDriverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return
                        d.findElement(By.xpath(location));
            }
        });
        return e;
    }

    public WebElement waitElementByAndroid(long wait, String location) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        WebElement e = webDriverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                AndroidDriver driver = (AndroidDriver) d;
                return
                        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + location + "\")");
            }
        });
        System.out.println(e.getText());
        return e;
    }


    /**
     * 等待指定text控件
     * @param text 控件的text
     * @param wait 等待时间
     * @return
     */
    public WebElement waitAndFindElement(String text, long wait){
        return waitAndFindElement(driver,text,wait);
    }

    public static WebElement waitAndFindElement(AndroidDriver driver,final String text, long wait){
        long startTime = System.currentTimeMillis();
        WebElement webElement = null;
        while (System.currentTimeMillis() - startTime < wait * 1000) {
            List<WebElement> elementList = new ArrayList<WebElement>();
            elementList = driver.findElementsByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
            if (elementList.size() > 0) {
                webElement = elementList.get(0);
                break;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return webElement;
    }


    public AndroidElement findElementByAndroidUIAutomator(String text){
        return (AndroidElement)driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+ text + "\")");
    }

    /*启动小程序*/
    public void launchMiniApp() throws InterruptedException {
        Allure.step("启动小程序");
        waitAndFindElement(findBtn, 5).click();
        waitAndFindElement(miniApp, 5).click();
        log.info(String.valueOf(System.currentTimeMillis()));
        waitAndFindElement(appName, 5).click();
        log.info(String.valueOf(System.currentTimeMillis()));

        assertEleExist( MallPage.member, 10);
    }

    /*杀掉应用进程，然后重启*/
    public void reLaunchApp() throws InterruptedException, IOException {
        Allure.step("重启动小程序");
        CmdUtil.runCommand("adb shell am force-stop com.tencent.mm");
        driver.launchApp();
        launchMiniApp();
    }

    public void assertEleExist(String text, int wait) {
        Assert.assertNotNull(waitAndFindElement(text, wait), text + "不存在");
    }

    @Attachment(value = "{text}",type = "image/png")
    public byte[]  takePhoto(String text, int wait) throws InterruptedException {
        if (wait > 0){
            Thread.sleep(wait*1000);
        }
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }

    @Attachment(value = "{text}",type = "image/png")
    public byte[]  takePhoto(String text)  {
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }

    /**
     * 切换输入法实现搜索
     */
    public static void Search(AndroidDriver driver, String text, String inputStr, int wait) {
        waitAndFindElement(driver,text,wait).sendKeys(inputStr);

        String result = "";
        String cmdstr = "adb shell ime set " + inputethod;
        try {
            CmdUtil.runCommand(cmdstr);
            Thread.sleep(3000);
            CmdUtil.runCommand("adb shell input keyevent 66");
            Thread.sleep(3000);
            result = CmdUtil.runCommand("adb shell ime set io.appium.android.ime/.UnicodeIME");
            if (!result.contains("Input method")) {
                System.out.println("************切换输入法失败！**************");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}