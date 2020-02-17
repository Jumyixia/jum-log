package com.jum.testcase;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class Test01 {

    @Test
    public void test0001() {
        log.debug("testcase-debug ");
        log.info("testcase-info 这个问你");
        log.warn("testcase-warn");
    }
}
