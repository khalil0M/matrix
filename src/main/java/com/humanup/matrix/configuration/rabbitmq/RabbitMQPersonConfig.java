package com.humanup.matrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPersonConfig {

  @Value("${person.queue.name}")
  String queuePerson;

  @Value("${person.exchange.name}")
  String exchangePerson;

  @Value("${person.routing.key}")
  String routingkeyPerson;

  @Bean
  Queue queue() {
    return new Queue(queuePerson, false);
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(exchangePerson);
  }

  @Bean
  Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingkeyPerson);
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    final var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    return rabbitTemplate;
  }
}
