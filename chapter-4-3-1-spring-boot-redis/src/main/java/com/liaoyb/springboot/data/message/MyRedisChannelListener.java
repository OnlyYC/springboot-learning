package com.liaoyb.springboot.data.message;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 监听
 *
 * @author liaoyb
 * @date 2018-10-02 15:53
 */
public class MyRedisChannelListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channel = message.getChannel();
        byte[] bs = message.getBody();

        try {
            String content = new String(bs, "UTF-8");
            String topic = new String(channel, "UTF-8");
            //打印
            System.out.println("get " + content + " from " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
