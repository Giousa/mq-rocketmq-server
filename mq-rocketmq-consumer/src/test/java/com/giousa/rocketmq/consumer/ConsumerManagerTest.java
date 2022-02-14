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
}