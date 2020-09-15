package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
//@RibbonClient(name="CLOUD-PROVIDER-SERVICE",configuration=MySelfRule.class)
public class OrderApp {
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}
}
