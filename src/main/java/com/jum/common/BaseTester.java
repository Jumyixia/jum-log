package com.jum.common;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Slf4j
@Listeners(TestFailListener.class)
public class BaseTester extends BaseManager {


    @BeforeSuite
    public void StartServer() throws Exception {
        StartAppiumServer();
    }

    @AfterSuite
    public void destroyServer() {
        destroyAppiumServer();
    }

    public AndroidDriver<WebElement> getDriver() {
        return driver;
    }

    @BeforeClass
    public synchronized void setUp() throws MalformedURLException {
        log.info("----------------setUp");
        //初始化
        StartAndroidDriver();

    }

    @AfterClass
    public void tearDown() throws Exception {
        log.info("----------------tearDown");
        getDriver().quit();
    }
}

