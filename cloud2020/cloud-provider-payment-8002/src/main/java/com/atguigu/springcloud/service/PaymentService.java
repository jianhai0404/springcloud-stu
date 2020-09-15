package com.atguigu.springcloud.service;

import java.util.List;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

	List<Payment> paymentList();

	int create(Payment payment);

	Payment getPaymentById(long id);

}
