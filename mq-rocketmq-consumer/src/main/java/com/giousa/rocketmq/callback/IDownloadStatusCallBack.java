package com.giousa.rocketmq.callback;

/**
 * 下载文件回调方法
 */
public interface IDownloadStatusCallBack {

    /**
     * 开始下载
     */
    void startDownload();

    /**
     * 停止下载
     */
    void stopDownload();

    /**
     * 展示下载地址
     */
    void showDownloadURL(String url);

    /**
     * 展示下载进度
     */
    void showDownloadProgress(int progress);
}
