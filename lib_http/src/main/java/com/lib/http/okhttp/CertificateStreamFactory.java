package com.lib.http.okhttp;

import com.lib.http.manager.HttpManager;

import java.io.IOException;
import java.io.InputStream;

import okio.Buffer;

/**
 * Description:
 * Company:
 * Email:
 * Created by Devin Sun on 2017/4/1.
 */
public class CertificateStreamFactory {



    public static InputStream[] trustedCertificatesInputStream(String[] str) {

        InputStream[] inputStream = new InputStream[str.length];
        for (int i = 0; i < str.length; i++) {
            inputStream[i] = new Buffer()
                    .writeUtf8(str[i])
                    .inputStream();
        }
        return inputStream;
    }


    public static InputStream[] trustedCertificatesInputStream(String assetFileName) {

        InputStream[] inputStream = new InputStream[1];
        try {
            inputStream[0] = HttpManager.context.getAssets().open(assetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
