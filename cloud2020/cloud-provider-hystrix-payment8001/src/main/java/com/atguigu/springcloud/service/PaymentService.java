package com.atguigu.springcloud.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

@Service
public class PaymentService {
	/**
	 * 正常访问
	 * @param id
	 * @return
	 */
	//======================服务降级
	public String paymentInfo_OK(Integer id) {
		return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
	}
	@HystrixCommand(fallbackMethod = "paymentInfo_timegoutHandler",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
	})
	public String paymentInfo_timegout(Integer id) {
		int timeNumber = 3;
//		int age = 10/0;
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池："+Thread.currentThread().getName()+" paymentInfo_timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"耗时（秒）"+timeNumber;
	}
	public String paymentInfo_timegoutHandler(Integer id) {
		return "线程池："+Thread.currentThread().getName()+" paymentInfo_timeoutHandler,id："+id+"\t"+"系统繁忙,系统运行报错┭┮﹏┭┮";
	}
	//======================服务熔断
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
		@HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
		@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" ,value = "10"),//请求次数
		@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000") ,//时间窗口期
		@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少后跳闸
	})//10秒中的时间窗口期10次访问假设超过百分之60就跳闸
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		if(id < 0) {
			throw new RuntimeException("********id 不能负数");
		}
		String serialNumber = IdUtil.simpleUUID();
		return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
	}
	public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
		return "id 不能负数，请稍后再试，o(╥﹏╥)o id："+id;
	}
}
