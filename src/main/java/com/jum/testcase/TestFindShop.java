package com.jum.testcase;

import com.jum.pageoptions.FindShopOp;
import com.jum.common.BaseTester;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Slf4j
@Epic("找店模块")
public class TestFindShop extends BaseTester {

    @Test
    @Story("详情页相关")
    @Description("进入零售店铺详情页")
    public void EnterShopTest() throws InterruptedException {
        log.info("start:");
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='发现']").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='小程序']").click();
        Thread.sleep(2000);
        //进入小程序
        driver.findElementByXPath("//*[@text='IN商圈']").click();
        Thread.sleep(8000);
//        FindShopOp.EnterFindShop(driver, CommonOp.wait5);
//        FindShopOp.EnterFindShop(driver, new WebDriverWait(driver, 5));
        FindShopOp.EnterFindShop(driver, new WebDriverWait(driver, 5));
    }
}
