package com.liaoyb.springboot.data.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 配置消息监听器
 *
 * @author liaoyb
 * @date 2018-10-02 15:59
 */
@Configuration
public class MessageConfig {
    /**
     * MessageListenerAdapter主要对消息进行序列化工作，默认采用StringRedisSerializer
     *
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(){
        return new MessageListenerAdapter(new MyRedisChannelListener());
        //可以指定序列化机制(默认StringRedisSerializer)
    }

    /**
     * RedisMessageListenerContainer通过PatternTopic派发消息到对应的消息监听者，并构造一个线程池任务来调用MessageListener
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("news.*"));
        return container;
    }
}
