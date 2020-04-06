package com.wenqiao;

import com.wenqiao.config.MQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	//通过rabbitAdmin来创建 删除queue exchange binging
	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Test
	public void testCreate(){
		//创建queue
		rabbitAdmin.declareQueue(new Queue("admin.queue"));
		//创建direct交换器
		rabbitAdmin.declareExchange(new DirectExchange("admin.exchange"));
		//创建binding
		//String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments
		rabbitAdmin.declareBinding(new Binding("admin.queue",Binding.DestinationType.QUEUE,"admin.exchange",
				"admin.routingkey",null));
	}
	@Test
	public void testSend(){
		Map<String,String> map = new HashMap<>();
		map.put("name","zhangsan");
		rabbitTemplate.convertAndSend("admin.exchange","admin.routingkey",map);
	}

	@Test
	public void testReceiver(){
		Object o = rabbitTemplate.receiveAndConvert("admin.queue");
		System.out.println(o);
	}


	@Test
	public void sendDirectMessage() {
		//String exchange, String routingKey, Object object
		Map<String,String> map = new HashMap<>();
		map.put("name","zhangsan");
		map.put("age","19");
		rabbitTemplate.convertAndSend(MQConfig.DIRECT_EXCHANGE,MQConfig.DIRECT_ROUTEKEY,map);
	}

	@Test
	public void receiveDirectMessage() {
		//String exchange, String routingKey, Object object
		Object object = rabbitTemplate.receiveAndConvert(MQConfig.DIRECT_QUEUE);
		System.out.println(object.getClass());
		System.out.println(object);
	}

	@Test
	public void sendTopicMessage() {
		//String exchange, String routingKey, Object object
		Map<String,String> map = new HashMap<>();
		map.put("topic","zhangsan");
		rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,MQConfig.TOPIC_ROUTEKEY,map);
	}

	@Test
	public void receiveTopicMessage1() {
		//String exchange, String routingKey, Object object
		Object object = rabbitTemplate.receiveAndConvert(MQConfig.TOPIC_QUEUE1);
		System.out.println(object.getClass());
		System.out.println(object);
	}
	@Test
	public void receiveTopicMessage2() {
		//String exchange, String routingKey, Object object
		Object object = rabbitTemplate.receiveAndConvert(MQConfig.TOPIC_QUEUE2);
		System.out.println(object.getClass());
		System.out.println(object);
	}


	@Test
	public void sendFanoutMessage() {
		Map<String,String> map = new HashMap<>();
		map.put("fanout","zhangsan");
		//路由设置为空,这个参数要填写
		rabbitTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",map);
	}

	@Test
	public void receiveFanoutMessage1() {
		//String exchange, String routingKey, Object object
		Object object = rabbitTemplate.receiveAndConvert(MQConfig.FANOUT_QUEUE1);
		System.out.println(object.getClass());
		System.out.println(object);
	}
	@Test
	public void receiveFanoutMessage2() {
		//String exchange, String routingKey, Object object
		Object object = rabbitTemplate.receiveAndConvert(MQConfig.FANOUT_QUEUE2);
		System.out.println(object.getClass());
		System.out.println(object);
	}

}
