package com.jum.common;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Listeners(TestFailListener.class)
public class BaseTester {

    public AndroidDriver<WebElement> driver;


    public BaseTester() {}

    public AndroidDriver<WebElement> getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        log.info("----------------setUp");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("automationName","Uiautomator2");
        capabilities.setCapability("deviceName", "MKJNW18419004359");//指定测试机的ID，通过adb devices获取
        //设置安卓系统版本
        capabilities.setCapability("platformVersion", "9");
        //设置apk路径
        capabilities.setCapability("sessionOverride", true);//每次启动时覆盖session，否则第二次后运行会报错不能新建session
        capabilities.setCapability("noReset", "true");//每次启动时不清楚应用数据启动（不需要每次微信登录、授权等）

        capabilities.setCapability("unicodeKeyboard", true);//是使用unicode编码方式发送字符串
        capabilities.setCapability("resetKeyboard", true);
        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", "com.tencent.mm");
        capabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        capabilities.setCapability("appWaitActivity", "com.tencent.mm.ui.LauncherUI");
        capabilities.setCapability("androidProcess", "com.tencent.mm:apbrand2");
        //初始化
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterClass
    public void tearDown() throws Exception {
        log.info("----------------tearDown");
        driver.quit();
    }
}

