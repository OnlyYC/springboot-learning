# RedisTemplate支持Pub/Sub功能

## 配置监听
实现MessageListener的onMessage方法:
```java
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
```

添加java配置来设置监听器
```java
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
```


## 调用convertAndSend方法发送消息
```java
redisTemplate.convertAndSend("news.redis", "hello,world");
```