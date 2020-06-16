package com.wdy.yunplm.productItem.service;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.productItem.service.impl.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "order-server-8002", fallback = OrderServiceFallback.class)
public interface IOrderService {
	@GetMapping("/api/v1/order/startProduce/{orderId}")
	Result<Order> modifyOrderStatusToProduceByOrderId_Feign(@PathVariable Integer orderId);
}

