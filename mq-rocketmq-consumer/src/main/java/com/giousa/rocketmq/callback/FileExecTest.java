package com.giousa.rocketmq.callback;

public class FileExecTest {

    public static void main(String[] args) {
        CallBackImpl callBackImpl = new CallBackImpl();
        callBackImpl.download("https://www.baidu.com");
    }
}
