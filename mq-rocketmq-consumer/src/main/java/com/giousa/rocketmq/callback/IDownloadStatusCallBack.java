package com.giousa.rocketmq.callback;

/**
 * 下载文件回调方法
 */
public interface IDownloadStatusCallBack {

    /**
     * 开始下载
     */
    public void startDownload();

    /**
     * 停止下载
     */
    public void stopDownload();

    /**
     * 展示下载地址
     */
    public void showDownloadURL(String url);

    /**
     * 展示下载进度
     */
    public void showDownloadProgress(int progress);
}
