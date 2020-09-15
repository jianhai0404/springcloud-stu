package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderFeignController {
	
	@Resource
	private PaymentFeignService paymentFeignService;
	
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable long id){
//		return this.restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
		return this.paymentFeignService.getPaymentById(id);
	}
	@GetMapping("/consumer/payment/timeout")
	public String paymentFeignTimeout() {
		return this.paymentFeignService.paymentFeignTimeout();
	}
}
