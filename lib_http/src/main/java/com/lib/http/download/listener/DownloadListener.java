package com.lib.http.download.listener;

import java.io.File;

/**
 * Description:
 * Company:
 * Email:
 * Created by Devin Sun on 2017/4/3.
 */
public interface DownloadListener {

    void onProgress(long bytesRead, long contentLength);

    void onSuccess(File file);

    void onFailure(Throwable t);
}
