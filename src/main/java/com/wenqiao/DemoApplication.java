package com.wenqiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitmqAutoConfiguration
 * 有自动配置的连接工产ConnectionFactory
 * RabbitmqProperties封装了RabbitMQ配置
 * rabbitTemplate,给java提供了操作模板
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}