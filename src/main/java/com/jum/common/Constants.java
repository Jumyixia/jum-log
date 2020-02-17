package com.jum.common;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants extends BaseTester {

    public  WebDriverWait wait5 = new WebDriverWait(driver, 5);
    public  WebDriverWait wait10  = new WebDriverWait(driver, 10);
    public  WebDriverWait wait20  = new WebDriverWait(driver, 20);
}
