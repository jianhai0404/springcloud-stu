package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableHystrix
public class OrderHyxtrxMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderHyxtrxMain80.class, args);
	}
}
