package com.wdy.yunplm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Logistics_App_8003 {
	public static void main(String[] args) {
		SpringApplication.run(Logistics_App_8003.class, args);
	}
}
