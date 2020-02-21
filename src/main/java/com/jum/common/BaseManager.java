package com.jum.common;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class BaseManager {
    public AndroidDriver<WebElement> driver;
    public static AppiumManager appiumMan=new AppiumManager();
    public static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartAppiumServer() throws Exception {
        appiumMan.appiumServerForAndroid("123098", "qwepoi");
    }

    public void destroyAppiumServer() {
        appiumMan.destroyAppiumNode();
    }

    public void StartAndroidDriver() {
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new AndroidDriver<>(appiumMan.getAppiumUrl(), AndroidCap());
    }

    public Capabilities AndroidCap() {
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
        return capabilities;
    }

}
