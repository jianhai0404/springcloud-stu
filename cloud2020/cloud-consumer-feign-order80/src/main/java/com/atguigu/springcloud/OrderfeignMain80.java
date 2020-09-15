package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@EnableEurekaClient
@EnableFeignClients
public class OrderfeignMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderfeignMain80.class, args);
	}
}
