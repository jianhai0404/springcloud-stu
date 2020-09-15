package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
	
	@Resource
	private PaymentHystrixService paymentHystrixService;
	
	@GetMapping("/consumer/paymenthystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		return this.paymentHystrixService.paymentInfo_OK(id);
	}
	
	@GetMapping("/consumer/paymenthystrix/timeout/{id}")
//	@HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//	})
	@HystrixCommand//如果不指定该方法专属fallback就要加上该注解才能找到全局fallback
	public String paymentInfo_timeout(@PathVariable("id") Integer id) {
		return this.paymentHystrixService.paymentInfo_timeout(id);
	}
	public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id) {
		return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
	}
	
	//下面是全局fallback
	public String payment_Global_FallbackMethod() {
		return "Global异常处理信息，请稍后重试，o(╥﹏╥)o";
	}
}
