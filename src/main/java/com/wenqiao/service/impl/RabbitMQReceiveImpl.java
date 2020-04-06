package com.wenqiao.service.impl;

import com.wenqiao.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiveImpl {

    private static Logger log = LoggerFactory.getLogger(RabbitMQReceiveImpl.class);

    @RabbitListener(queues = MQConfig.DIRECT_QUEUE)
    public void receive(Object message){
        log.info("receive message:"+message);
    }



}
