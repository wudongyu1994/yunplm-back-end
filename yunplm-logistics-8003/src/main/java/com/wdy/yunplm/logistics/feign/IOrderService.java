package com.wdy.yunplm.logistics.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "order-server-8002", contextId = "from8003to8002", fallback = OrderServiceFallback.class)
public interface IOrderService {
	@GetMapping("/api/v1/order/expressNo")
	Result<Order> getOrderByExpressNo_Feign(@RequestParam("expressNo") String expressNo);

	@PutMapping("/api/v1/order/{id}")
	Result<Order> modifyOrderById_Feign(@PathVariable Integer id, @RequestBody Order order);

	@GetMapping("/api/v1/order/startDelivery/{orderId}/{deliveryTime}")
	Result<Order> modifyOrderStatusToDeliveryByOrderId_Feign(@PathVariable Integer orderId, @PathVariable Long deliveryTime);

}