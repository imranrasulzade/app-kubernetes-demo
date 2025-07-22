package com.example.appkubernetesdemo.producer;

import com.example.appkubernetesdemo.config.RabbitConfig;
import com.example.appkubernetesdemo.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public void sendPayment(Payment payment) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                payment
        );
        log.info("Payment pub to queue: {}", payment);
    }
}
