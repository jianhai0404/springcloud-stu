package com.atguigu.springcloud.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

public interface LoadBalanced {
	ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
