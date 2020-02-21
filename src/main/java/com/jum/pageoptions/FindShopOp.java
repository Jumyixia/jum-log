package com.jum.pageoptions;

import com.jum.common.CommonStep;
import com.jum.page.FindShopPage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@Slf4j
public class FindShopOp {
    public AndroidDriver<WebElement> driver;

    public FindShopOp(AndroidDriver<WebElement> driver) {
        this.driver = driver;
    }

    public void searchByName(String text){
        Allure.step("输入并搜索");
        CommonStep.Search(driver,FindShopPage.searchInput, text, 8);
    }

}
