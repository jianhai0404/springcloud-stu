package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderconsulController {
	
	public static final String PAYMENT_URL = "http://consul-provider-payment";
	
	@Resource
	private RestTemplate restTemplate;
	
//	@GetMapping("/consumer/payment/create")
//	public CommonResult<Payment> create(Payment payment){
//		return this.restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
//	}
//	
//	@GetMapping("/consumer/payment/get/{id}")
//	public CommonResult<Payment> getPayment(@PathVariable long id){
//		return this.restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
//	}
	@GetMapping("/payment/consul")
	public String paymentInfo() {
		return restTemplate.getForObject(PAYMENT_URL+"/payment/consul", String.class);
	}
	
}
