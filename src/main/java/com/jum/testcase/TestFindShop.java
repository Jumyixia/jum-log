package com.jum.testcase;

import com.jum.common.CommonStep;
import com.jum.page.FindShopPage;
import com.jum.pageoptions.FindShopOp;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Epic("找店模块")
public class TestFindShop extends CommonStep {

    @Test
    @Story("详情页相关")
    @Description("进入零售店铺详情页")
    public void EnterShopTest() throws InterruptedException, IOException {
        launchMiniApp();
        waitAndFindElement(FindShopPage.findShopBtn, 5).click();

    }

    @Test
    @Story("查询与筛选")
    @Description("按位置筛选")
    public void FilterByLocation() throws InterruptedException, IOException {
        Allure.parameter("", "筛选条件=高层/G1");
        reLaunchApp();
        waitAndFindElement(FindShopPage.findShopBtn, 5).click();

        Allure.step("按位置筛选");
        waitAndFindElement(FindShopPage.locationBtn, 2).click();
        findElementByAndroidUIAutomator("高层").click();
        findElementByAndroidUIAutomator("G1").click();
        findElementByAndroidUIAutomator("确定").click();

        takePhoto("筛选结果截图",6);


        WebElement shopName = waitAndFindElement("红牛新零售测试店", 2);

    }

    @Test
    @Story("查询与筛选")
    @Description("按名称搜索，中文")
    public void SearchShop() throws InterruptedException, IOException {
        String text = "测试";
        Allure.parameter("", "搜索条件=" + text);
        reLaunchApp();
        waitAndFindElement(FindShopPage.findShopBtn, 5).click();

        CommonStep.Search(driver,FindShopPage.searchInput, text, 8);

        takePhoto("查询结果截图",6);
    }

    @Test
    @Story("查询与筛选")
    @Description("按名称搜索，英文")
    public void SearchShop2() throws InterruptedException, IOException {
        String text = "shop";
        Allure.parameter("搜索条件", text);
        reLaunchApp();
        waitAndFindElement(FindShopPage.findShopBtn, 5).click();

        CommonStep.Search(driver,FindShopPage.searchInput, text, 8);

        takePhoto("查询结果截图",6);

    }

    @Test
    @Story("查询与筛选")
    @Description("按名称搜索，无结果")
    public void SearchShop3() throws InterruptedException, IOException {
        String text = "不存在";
        Allure.parameter("筛选条件", "高层/G1");
        reLaunchApp();
        waitAndFindElement(FindShopPage.findShopBtn, 5).click();

        CommonStep.Search(driver,FindShopPage.searchInput, text, 8);

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        findElementByAndroidUIAutomator(FindShopPage.searchResultEmpty);
        takePhoto("查询结果截图");

    }
}