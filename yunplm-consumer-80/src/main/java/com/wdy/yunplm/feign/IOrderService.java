package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.feign.impl.MyFallback;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.vo.OrderStatus;
import com.wdy.yunplm.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "order-server-8002", fallback = MyFallback.class)
public interface IOrderService {
	@GetMapping("/api/v1/order/{orderId}")
	Result<Order> getOrderById_Feign(@PathVariable Integer orderId);

	@GetMapping("/api/v1/orderIncome")
	Result<List<Long>> getOderIncome_Feign();

	@GetMapping("/api/v1/orderStatus")
	Result<List<OrderStatus>> getAllOderStatus_Feign();

	@GetMapping("/api/v1/orderStatus/{userId}")
	Result<List<OrderStatus>> getOderStatusByUserId_Feign(@PathVariable Integer userId);

	@GetMapping("/api/v1/order")
	Result<List<Order>> getOrderByPage_Feign(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

	@GetMapping("/api/v1/orderByUserId/{userId}")
	Result<List<Order>> getOrderByUserIdByPage_Feign(@PathVariable Integer userId, @RequestParam("page") Integer page,
	                                                 @RequestParam("size") Integer size);

	@PostMapping("/api/v1/order")
	Result<Order> addOrder_Feign(@RequestBody OrderVO orderVO);

	@PutMapping("/api/v1/order/{id}")
	Result<Order> modifyOrderById_Feign(@PathVariable Integer id, @RequestBody Order order);

	@DeleteMapping("/api/v1/order/{id}")
	Result<Order> deleteOrderById_Feign(@PathVariable Integer id);
}
