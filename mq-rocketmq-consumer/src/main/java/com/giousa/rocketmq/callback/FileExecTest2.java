package com.giousa.rocketmq.callback;

public class FileExecTest2 {

    public static void main(String[] args) {
        FileServer fileServer = new FileServer(new IDownloadStatusCallBack() {
            @Override
            public void startDownload() {
                System.out.println("FileExecTest2. startDownload");
            }

            @Override
            public void stopDownload() {
                System.out.println("FileExecTest2. stopDownload");
            }

            @Override
            public void showDownloadURL(String url) {
                System.out.println("FileExecTest2. showDownloadURL url: " + url);
            }

            @Override
            public void showDownloadProgress(int progress) {
                System.out.println("FileExecTest2. showDownloadProgress  progress: " + progress);
            }
        }, "http://test.com");

        fileServer.Run();
    }
}
