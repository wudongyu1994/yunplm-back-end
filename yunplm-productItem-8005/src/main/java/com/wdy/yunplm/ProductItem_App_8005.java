package com.wdy.yunplm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductItem_App_8005 {
	public static void main(String[] args) {
		SpringApplication.run(ProductItem_App_8005.class, args);
	}
}
