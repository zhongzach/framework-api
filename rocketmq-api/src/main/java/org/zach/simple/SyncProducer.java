package org.zach.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/22 10:40 上午
 * @desc 同步发送消息，简单案例
 */
public class SyncProducer {

    public static final String PRODUCT_GROUP = "zach";

    private static final String NAMESRV_ADDR="49.235.226.61:9876";

    public static void main(String[] args) throws Exception {
        // 1、创建默认生产者，传入生产者组，默认是'DEFAULT_PRODUCER'
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCT_GROUP);
        // 2、设置namesrv地址（集群都填上）
        producer.setNamesrvAddr(NAMESRV_ADDR);
        // 3、启动生产者
        producer.start();

        // 模拟发送消息
        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

    }

}
