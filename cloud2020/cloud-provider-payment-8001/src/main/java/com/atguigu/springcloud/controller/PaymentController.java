package com.atguigu.springcloud.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Resource
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/getMessage")
	public List<Payment> paymentList(){
		return this.paymentService.paymentList();
	}
	
	@PostMapping("/create")
	//加上requestBody当请求参数过来时自动封装到该实体类
	public CommonResult<Payment> create(@RequestBody Payment payment){
		int result = this.paymentService.create(payment);
		log.info("*******插入结果："+result);
		if(result >0 ) {
			return new CommonResult(200,"插入数据成功,serverPort："+serverPort,result);
		}
		return new CommonResult<Payment>(444,"插入数据失败",null);
	}
	@GetMapping(value = "/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
		Payment payment = this.paymentService.getPaymentById(id);
		log.info("*******插入结果："+payment);
		if(payment != null) {
			return new CommonResult<Payment>(200,"查询成功,serverPort："+serverPort,payment);
		}
		return new CommonResult<Payment>(444,"没有对应记录",null);
	}
	
	@GetMapping("/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			log.info("****element"+element);
		}
		
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
		for (ServiceInstance instance : instances) {
			log.info("****"+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
		}
		return this.discoveryClient;
	}
	@GetMapping("/lb")
	public String getPaymentLB() {
		return this.serverPort;
	}
	
	@GetMapping("/feign/timeout")
	public String paymentFeignTimeout() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return serverPort;
	}
}
