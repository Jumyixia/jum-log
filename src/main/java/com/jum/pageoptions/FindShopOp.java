package com.jum.pageoptions;

import com.jum.pages.FindShopPage;
import com.jum.common.CommonOp;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class FindShopOp {

    /*找店 按钮*/
    public static String FindShopBtn = "找店";
    public static String FindShopBtnXpath = "//*[@text='发现']";

    public static void EnterFindShop(AndroidDriver<WebElement> driver, WebDriverWait wait){
        log.debug("EnterFindShop");
        WebElement webElement =  driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + FindShopBtn+"\")");
        System.out.println(webElement.isSelected());
        CommonOp.waitAndFindElement(driver,5, FindShopBtn).click();

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         webElement =  driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + FindShopPage.FindShopBtn+"\")");
//webElement.click();
        System.out.println(webElement.isSelected());
        System.out.println(webElement.isEnabled());
    }
}
