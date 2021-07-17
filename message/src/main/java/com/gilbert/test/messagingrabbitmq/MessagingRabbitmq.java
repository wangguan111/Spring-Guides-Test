package com.gilbert.test.messagingrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
public class MessagingRabbitmq {

	static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";
	static final String QUEUE_NAME = "spring-boot";
	static final String ROUTING_KEY = "foo.bar.#";

	static final String DEFAULT_METHOD = "listener";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean("rabbitmqContainer")
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean("rabbitmqListenerAdapter")
	MessageListenerAdapter listenerAdapter(MessagingRabbitmqListener messagingRabbitmqListener) {
		return new MessageListenerAdapter(messagingRabbitmqListener, DEFAULT_METHOD);
	}
}
