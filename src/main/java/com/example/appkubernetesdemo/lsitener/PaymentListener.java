package com.example.appkubernetesdemo.lsitener;

import com.example.appkubernetesdemo.config.RabbitConfig;
import com.example.appkubernetesdemo.entity.Payment;
import com.example.appkubernetesdemo.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentListener.class);
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receivePayment(Payment payment) {
        log.info("Received payment {}", payment);
        paymentRepository.save(payment);
        log.info("payment saved to db");
    }
}
