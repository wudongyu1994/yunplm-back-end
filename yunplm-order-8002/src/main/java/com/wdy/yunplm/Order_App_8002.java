package com.wdy.yunplm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Order_App_8002 {
	public static void main(String[] args) {
		SpringApplication.run(Order_App_8002.class, args);
	}
}
