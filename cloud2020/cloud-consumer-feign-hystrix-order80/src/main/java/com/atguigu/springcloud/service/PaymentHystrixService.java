package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
	@GetMapping("/paymenthystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id);
	@GetMapping("/paymenthystrix/timeout/{id}")
	public String paymentInfo_timeout(@PathVariable("id") Integer id);
}
