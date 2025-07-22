package com.example.appkubernetesdemo.repository;

import com.example.appkubernetesdemo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
