package com.giousa.rocketmq;

import com.giousa.common.utils.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println("RocketMQ 项目启动：" + DateUtil.dateToString(new Date(), null));
    }

}
