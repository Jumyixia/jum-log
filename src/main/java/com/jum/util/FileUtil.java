package com.jum.util;

import java.io.File;

public class FileUtil {
    /**
     * 自动生成文件夹,确保文件创建成功
     * @param filePath 文件全路径
     * @return
     */
    public static File makeDir(String filePath) {
        File file = new File(filePath);
        String[] dirArray = filePath.split("/");
        if (!file.exists()) {
            makeDir(file.getParent());
            System.out.println(dirArray[dirArray.length-1]);
            if (!dirArray[dirArray.length-1].contains(".")) {
                file.mkdir();
            }
        }
        return file;
    }
}
