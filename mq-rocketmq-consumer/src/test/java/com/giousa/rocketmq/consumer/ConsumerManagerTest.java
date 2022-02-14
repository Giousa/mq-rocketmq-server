package com.giousa.rocketmq.consumer;

import com.giousa.rocketmq.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class ConsumerManagerTest extends BaseTest {

    @Resource
    private ConsumerManager consumerManager;

    @Test
    public void baseConsumerListenerTest() {
        consumerManager.baseConsumerListener();
    }

    @Test
    public void systemSetTest() {
        try {
            System.setProperty("property_fun", "systemSetTest");
            System.out.println("设置属性成功！");
        }catch (Exception e){
            System.out.println("设置属性失败");
            System.out.println(e);
        }
    }

    /**
     * 只有在项目启动的时候设置，有效
     */
    @Test
    public void systemGetTest() {
        systemSetTest();
        String property_fun = System.getProperty("property_fun");
        System.out.println("获取系统属性值：" + property_fun);

    }
}