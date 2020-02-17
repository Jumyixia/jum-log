package com.jum.testcase;

import com.jum.common.BaseTester;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
@Epic("会员码")
public class TestMiniApp extends BaseTester {

    @Test(description = "启动小程序")
//    @Epic("肉圆 Epic")
//    @Story("肉圆 story")
    @Step("肉圆 Step")
    @Description("Start 小程序")
    public void StartTestCase() throws InterruptedException {
        log.info("start:");
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='发现']").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='小程序']").click();
        Thread.sleep(2000);
        //进入小程序
        driver.findElementByXPath("//*[@text='IN商圈']").click();
        Thread.sleep(8000);

        log.info("click member");
        log.debug(String.valueOf(System.currentTimeMillis()));
//        driver.findElementByXPath("//*[@text='会员码']").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"会员码\")").click();
        log.debug(String.valueOf(System.currentTimeMillis()));
        Thread.sleep(2000);

        WebElement element = (WebElement) driver.findElementsByClassName("android.view.View").get(3);
        log.info(element.getText());
        Assert.assertEquals("testcase", element.getText(), "校验错误");
//        Thread.sleep(2000);
        log.info("StartTestCase end");
    }

    @Test
    public void test3() {
        Assert.assertEquals(true, true, "true false assert fail test3");
    }
}
