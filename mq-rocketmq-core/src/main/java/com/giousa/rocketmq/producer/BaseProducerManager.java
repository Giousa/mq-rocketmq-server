package com.giousa.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseProducerManager {

    @Value("${server.port}")
    private Integer port;

    public void sendAsyceMsg(String msg) {
        System.out.println("port = "+port);
        try {
            //1.创建消息生产者producer，并制定生产者组名
            DefaultMQProducer producer = new DefaultMQProducer("PRODUCERGROUP_ASYNC");

            //2.指定Nameserver地址
            producer.setNamesrvAddr("139.224.46.106:9876");

            //重试次数
            producer.setRetryTimesWhenSendFailed(3);

            //3.启动producer
            producer.start();

            //4.创建消息对象，指定主题Topic、Tag和消息体
            Message message = new Message("base_async", "async_tag_1", msg.getBytes());
            log.info("发送数据：msg: {}, message: {}", msg, JSON.toJSONString(message));

            //延迟消息
            message.setDelayTimeLevel(5);

            //5.发送异步消息
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    log.info("sendAsyceMsg send success. msg: {},result: {}", msg, sendResult);
                }

                public void onException(Throwable e) {
                    log.info("sendAsyceMsg send fail. msg: {},e: {}", msg, e);
                }
            });

            //异步，需要延迟一点时间，然后再关闭，否则发送失败
            Thread.sleep(2000L);

            //6.关闭生产者producer
            producer.shutdown();
        } catch (Exception e) {
            log.warn("sendAsyceMsg error. msg: {},e: {}", msg, e);
        }
    }
}
