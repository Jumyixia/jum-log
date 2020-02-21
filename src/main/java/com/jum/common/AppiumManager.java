package com.jum.common;

import com.jum.util.AvailabelPorts;
import com.jum.util.CommandPrompt;
import com.jum.util.FileUtil;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import lombok.extern.slf4j.Slf4j;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Appium Manager - this class contains method to start and stops appium server
 * To execute the tests from eclipse, you need to set PATH as
 * /usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin in run configuration
 */
@Slf4j
public class AppiumManager {
    CommandPrompt cp = new CommandPrompt();
    AvailabelPorts ap = new AvailabelPorts();
    public AppiumDriverLocalService appiumDriverLocalService;
    public Properties prop = new Properties();
//    public AppiumServiceBuilder builder = new AppiumServiceBuilder();
    public File appiumLog;

    /**
     * start appium with auto generated ports : appium port, chrome port,
     * bootstrap port and device UDID
     */
    public AppiumServiceBuilder appiumServerForAndroid(String deviceID, String methodName) throws Exception {
        log.info("**************************************************************************\n");
        log.info("Starting Appium Server to handle Android Device::" + deviceID + "\n");
        log.info("**************************************************************************\n");
        prop.load(new FileInputStream("config.properties"));
        int port = ap.getPort();
        int chromePort = ap.getPort();
        int bootstrapPort = ap.getPort();
        int selendroidPort = ap.getPort();
        appiumLog = FileUtil.makeDir(System.getProperty("user.dir") + "/target/appiumlogs/" + deviceID
                .replaceAll("\\W", "_") + "__" + methodName + ".txt");
        log.info("appiumLog: " + appiumLog);
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                        .withAppiumJS(new File(prop.getProperty("APPIUM_JS_PATH")))
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info").withLogFile(appiumLog)
                        .withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, Integer.toString(chromePort))
                        .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPort))
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
                        .withArgument(AndroidServerFlag.SELENDROID_PORT, Integer.toString(selendroidPort))
                        .usingPort(port);
        appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
        log.info(String.valueOf(appiumDriverLocalService.isRunning()));
        return builder;

    }

    /**
     * start appium with auto generated ports : appium port, chrome port,
     * bootstrap port and device UDID
     */
    ServerArgument webKitProxy = new ServerArgument() {
        @Override public String getArgument() {
            return "--webkit-debug-proxy-port";
        }
    };

    /**
     * 获取appium服务的url
     * @return appium服务的url
     */
    public URL getAppiumUrl() {
        log.info(String.valueOf(appiumDriverLocalService.isRunning()));
        return appiumDriverLocalService.getUrl();
    }

    /**
     * 关闭appium服务
     */
    public void destroyAppiumNode() {
        appiumDriverLocalService.stop();
        if (appiumDriverLocalService.isRunning()) {
            log.info("AppiumServer didn't shut... Trying to quit again....");
            appiumDriverLocalService.stop();
        }
    }
    public File getAppiumLog(){
        return appiumLog;
    }
}
