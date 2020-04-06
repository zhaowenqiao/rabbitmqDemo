package com.wenqiao.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    /**direct模式--start**/
    public static final String DIRECT_QUEUE = "queue";
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String DIRECT_ROUTEKEY = "directRouteKey";

    @Bean
    public Queue directQueue(){
        return new Queue(DIRECT_QUEUE,true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinding(){
       return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTEKEY);
    }
    /**direct模式--end**/

    /**topic模式--start**/
    public static final String TOPIC_QUEUE1 = "topicQueue1";
    public static final String TOPIC_QUEUE2 = "topicQueue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String TOPIC_ROUTEKEY = "topicRoutekey#";//#是匹配多个,*匹配一个

    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_ROUTEKEY);
    }
    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_ROUTEKEY);
    }
    /**topic模式--end**/


    /**fount模式--start 不用路由的 效率比topic稍微高点**/
    public static final String FANOUT_QUEUE1 = "fanoutQueue1";
    public static final String FANOUT_QUEUE2 = "fanoutQueue2";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE1,true);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE2,true);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
