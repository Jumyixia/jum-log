package com.jum.pageoptions;

import com.jum.common.CommonStep;
import com.jum.util.AssertUtil;
import com.jum.util.CmdUtil;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import java.io.IOException;

@Slf4j
public class BaseOp {
    public static AndroidDriver<WebElement> driver;
    public static String findBtn = "发现";
    public static String miniApp = "小程序";
    public static String appName = "IN商圈";

    public BaseOp(AndroidDriver<WebElement> driver){
        this.driver = driver;
    }


}
