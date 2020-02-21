package com.jum.testcase;

import com.jum.util.CmdUtil;
import com.jum.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.*;

@Slf4j
public class Test01 {

    @Test
    public void test0001() {
        log.debug("testcase-debug ");
        log.info("testcase-info 这个问你");
        log.warn("testcase-warn");
    }

    @Test
    public void test0002(){
        String filepath = "/Users/recluse/Documents/JuYuren/jjj/uj/iuk.txt";
        FileUtil.makeDir(filepath);


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("rouyuantest");
            outputStreamWriter.flush();
            outputStreamWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test0003() throws IOException {
        log.info(CmdUtil.runCommand("adb devices"));

        log.info(CmdUtil.runCommand("adb shell am start -a android.settings.INPUT_METHOD_SETTINGS"));
    }
}
