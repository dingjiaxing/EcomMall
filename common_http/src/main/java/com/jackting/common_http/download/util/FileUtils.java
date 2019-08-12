package com.jackting.common_http.download.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Company:
 * Email:
 * Created by Devin Sun on 2017/4/20.
 */
public class FileUtils {
    /**
     * @param dirPath     文件夹路径
     * @param fileName    文件名
     * @param inputStream 输入流
     */
    public static void saveFile(String dirPath, String fileName, InputStream inputStream) throws IOException {

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }

        byte[] buf = new byte[3072];
        int len;
        FileOutputStream fos = new FileOutputStream(file);
        while ((len = inputStream.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        inputStream.close();
        fos.flush();

    }
}
