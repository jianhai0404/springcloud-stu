package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyLogGatewayFilter implements GlobalFilter,Ordered {

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String uname = exchange.getRequest().getQueryParams().getFirst("uname");
		if(uname==null) {
			System.out.println("用户名为null，非法用户，o(╥﹏╥)o");
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

}
