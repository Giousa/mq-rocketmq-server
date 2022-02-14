package com.giousa.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsumerManager {

    private DefaultMQPushConsumer consumer;

    public void baseConsumerListener() {
        try {
            //1.创建消费者Consumer，制定消费者组名
            consumer = new DefaultMQPushConsumer("PRODUCERGROUP_ASYNC");
            //2.指定Nameserver地址
            consumer.setNamesrvAddr("139.224.46.106:9876");
            //3.订阅主题Topic和Tag
            consumer.subscribe("base_async", "async_tag_1");

            //设定消费模式：负载均衡|广播模式   CLUSTERING BROADCASTING
            //负载均衡：各个终端，随机接收
            //广播：各个终端，接收全部，相同
            //默认是负载均衡
            consumer.setMessageModel(MessageModel.BROADCASTING);

            //4.设置回调函数，处理消息
            //接受消息内容
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                System.out.println("------------------receive msg------------------");
                for (MessageExt msg : msgs) {
                    log.info("consumerListener-v2 receive msg. body: {},thread: {}", new String(msg.getBody()), Thread.currentThread().getName());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            //5.启动消费者consumer
            consumer.start();
            log.info("consumerListener-v2 消费者 启动!");

            Scanner input = new Scanner(System.in);
            String next = input.next();
            System.out.println("next2: " + next);

        } catch (Exception e) {
            log.warn("consumerListener-v2 error. e: {}", e);
        } finally {
            consumer.shutdown();
            log.info("consumerListener-v2 关闭消费者");
        }
    }
}
