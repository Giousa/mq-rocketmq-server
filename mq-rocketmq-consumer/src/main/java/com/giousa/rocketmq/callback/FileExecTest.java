package com.giousa.rocketmq.callback;

public class FileExecTest {

    public static void main(String[] args) {
        FileDownClient fileDownClient = new FileDownClient();
        fileDownClient.download("https://www.baidu.com");
    }
}
