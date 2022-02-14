package com.giousa.rocketmq.producer;


import com.giousa.rocketmq.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class BaseProducerManagerTest extends BaseTest {

    @Resource
    private BaseProducerManager baseProducerManager;

    @Test
    public void sendAsyceMsgTest() {
        baseProducerManager.sendAsyceMsg("测试_0124");
    }
}