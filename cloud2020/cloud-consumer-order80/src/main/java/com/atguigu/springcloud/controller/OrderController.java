package com.atguigu.springcloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalanced;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	
	public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	private LoadBalanced LoadBalanced;
	
	@Resource
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment){
		return this.restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
	}
	
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable long id){
		return this.restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
	}
	@GetMapping("/consumer/payment/getForEntity/{id}")
	public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
		ResponseEntity<CommonResult> entity = this.restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
		if(entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}
		return new CommonResult<>(444,"操作失败");
	}
	@GetMapping("/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
		if(instances == null || instances.size() <= 0) {
			return null;
		}
		ServiceInstance serviceInstance = this.LoadBalanced.instances(instances);
		URI uri = serviceInstance.getUri();
		log.info("********** uri: "+uri);
		return this.restTemplate.getForObject(uri+"/payment/lb", String.class);
	}
}