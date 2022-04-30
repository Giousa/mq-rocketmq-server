package com.giousa.rocketmq.callback;

public class CallBackImpl implements IDownloadStatusCallBack{

    private FileServer fileServer = null;

    public CallBackImpl() {
        // TODO Auto-generated constructor stub
    }

    public void download(String url) {
        fileServer = new FileServer(CallBackImpl.this, url);
        fileServer.Run();
    }

    @Override
    public void startDownload() {
        // TODO Auto-generated method stub
        System.out.println("startDownload");
    }

    @Override
    public void stopDownload() {
        // TODO Auto-generated method stub
        System.out.println("stopDownload");
    }

    @Override
    public void showDownloadURL(String url) {
        // TODO Auto-generated method stub
        System.out.println("Download URL: " + url);
    }

    @Override
    public void showDownloadProgress(int progress) {
        // TODO Auto-generated method stub
        System.out.println("DownloadProgress: " + progress + "%");
    }
}
