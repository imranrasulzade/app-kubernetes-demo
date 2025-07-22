package com.example.appkubernetesdemo.controller;

import com.example.appkubernetesdemo.entity.Payment;
import com.example.appkubernetesdemo.producer.PaymentProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentProducer paymentProducer;

    public PaymentController(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publish(@RequestBody Payment payment) {
        paymentProducer.sendPayment(payment);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
}
