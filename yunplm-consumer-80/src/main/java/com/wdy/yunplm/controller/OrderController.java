package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.feign.IOrderService;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.vo.OrderStatus;
import com.wdy.yunplm.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class OrderController extends ConsumerBaseApiController {
	@Resource
	private IOrderService iOrderService;

	@GetMapping("/order/{orderId}")
	public Result<Order> getOrderById(@PathVariable Integer orderId) {
		return iOrderService.getOrderById_Feign(orderId);
	}

	@GetMapping("/orderStatus")
	@PreAuthorize("hasAuthority('/order/admin')")
	public Result<List<OrderStatus>> getAllOderStatus() {
		return iOrderService.getAllOderStatus_Feign();
	}

	@GetMapping("/orderStatus/{userId}")
	@PreAuthorize("hasAuthority('/order/get')")
	public Result<List<OrderStatus>> getOderStatusByUserId(@PathVariable Integer userId) {
		return iOrderService.getOderStatusByUserId_Feign(userId);
	}

	@GetMapping("/orderIncome")
	@PreAuthorize("hasAuthority('/order/admin')")
	public Result<List<Long>> getOderIncome() {
		return iOrderService.getOderIncome_Feign();
	}

	@GetMapping("/order")
	@PreAuthorize("hasAuthority('/order/admin')")
	public Result<List<Order>> getOrderByPage(@RequestParam("page") Integer page,
	                                          @RequestParam("size") Integer size) {
		return iOrderService.getOrderByPage_Feign(page, size);
	}

	@GetMapping("/orderByUserId/{userId}")
	@PreAuthorize("hasAuthority('/order/get')")
	public Result<List<Order>> getOrderByUserIdByPage(@PathVariable Integer userId, @RequestParam("page") Integer page,
	                                                  @RequestParam("size") Integer size) {
		return iOrderService.getOrderByUserIdByPage_Feign(userId, page, size);
	}

	@PostMapping("/order")
	@PreAuthorize("hasAuthority('/order/add')")
	public Result<Order> addOrder(@RequestBody @Valid OrderVO orderVO,
	                              BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iOrderService.addOrder_Feign(orderVO);
	}

	@PutMapping("/order/{id}")
	@PreAuthorize("hasAuthority('/order/modify')")
	public Result<Order> modifyOrderById(@PathVariable Integer id,
	                                     @RequestBody Order order) {
		return iOrderService.modifyOrderById_Feign(id, order);
	}

	@DeleteMapping("/order/{id}")
	@PreAuthorize("hasAuthority('/order/delete')")
	public Result<Order> deleteOrderById(@PathVariable Integer id) {
		return iOrderService.deleteOrderById_Feign(id);
	}
}
