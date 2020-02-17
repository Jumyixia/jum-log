package com.jum.common;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
/**
 * 基础操作
 */
public class CommonOp extends BaseTester {

    public CommonOp() {
    }

    public static WebElement waitElementByXpath(AndroidDriver driver, long wait, String location) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        WebElement e = webDriverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return
                        d.findElement(By.xpath(location));
            }
        });
        System.out.println(e.getText());
        return e;
    }

    public static WebElement waitElementByAndroid(AndroidDriver driver, long wait, String location) {
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
     * @param driver 传入的driver
     * @param text 控件的text
     * @param wait 等待时间
     * @return
     */
    public static WebElement waitAndFindElement(AndroidDriver driver, long wait, String text){
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
}