package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
	/**
	 * @param args
	 * 用户监控服务提供者
	 * 监控哪些服务失败多少，成功多少
	 * 健康状况，是微服务的一个重要工具
	 */
	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardMain9001.class, args);
	}
}
