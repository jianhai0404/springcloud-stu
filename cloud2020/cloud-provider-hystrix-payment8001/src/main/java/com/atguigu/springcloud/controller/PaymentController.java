package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverProt;
	
	@GetMapping("/paymenthystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String result = this.paymentService.paymentInfo_OK(id);
		log.info("******result: "+result);
		return result;
	}
	@GetMapping("/paymenthystrix/timeout/{id}")
	public String paymentInfo_timeout(@PathVariable("id") Integer id) {
		String result = this.paymentService.paymentInfo_timegout(id);
		log.info("******result: "+result);
		return result;
	}
	//===服务熔断
	@GetMapping("/payment/circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		String result = this.paymentService.paymentCircuitBreaker(id);
		log.info("****result："+result);
		return result;
	}
}
