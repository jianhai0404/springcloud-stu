package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.mapper.PaymentDao;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;

	public List<Payment> paymentList() {
		return paymentDao.paymentList();
	}

	public int create(Payment payment) {
		return this.paymentDao.create(payment);
	}

	public Payment getPaymentById(long id) {
		return this.paymentDao.getPaymentById(id);
	}

}
