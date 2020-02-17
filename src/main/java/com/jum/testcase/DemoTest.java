package com.jum.testcase;

import com.jum.common.BaseTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest extends BaseTester {
    @Test
    public void test1(){
        Assert.assertEquals(true,false,"true false assert fail");
    }

    @Test
    public void test2(){
        Assert.assertEquals(true,true,"true false assert fail");
    }
}
