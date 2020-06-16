package com.wdy.yunplm.order.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.order.service.IOrderService;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.vo.OrderStatus;
import com.wdy.yunplm.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class OrderController extends BaseApiController {
	@Resource
	private IOrderService iOrderService;

	@GetMapping("/order/{orderId}")
	public Result<Order> getOrderById(@PathVariable Integer orderId) {
		Order order = iOrderService.getOrderById(orderId);
		if (order == null) return Result.failure();
		return Result.success(order);
	}

	@GetMapping("/orderStatus")
	public Result<List<OrderStatus>> getAllOderStatus() {
		List<OrderStatus> list = iOrderService.getAllOderStatus();

		return Result.success(list, list.size());
	}

	@GetMapping("/orderStatus/{userId}")
	public Result<List<OrderStatus>> getOderStatusByUserId(@PathVariable Integer userId) {
		List<OrderStatus> list = iOrderService.getOderStatusByUserId(userId);
		return Result.success(list, list.size());
	}

	@GetMapping("/orderIncome")
	public Result<List<Long>> getOderIncome() {
		List<Long> list = iOrderService.getOderIncome();
		return Result.success(list, list.size());
	}

	@GetMapping("/order")
	public Result<List<Order>> getOrderByPage(@RequestParam("page") Integer page,
	                                          @RequestParam("size") Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
		List<Order> orderList = iOrderService.getOrderByPage(pageable);
		Long total = iOrderService.getOrderTotalNumber();
		if (total == null) return Result.failure();
		return Result.success(orderList, iOrderService.getOrderTotalNumber().intValue());
	}

	@GetMapping("/order/expressNo")
	public Result<Order> getOrderByExpressNo_Feign(@RequestParam("expressNo") String expressNo) {
		Order order = iOrderService.getOrderByExpressNo(expressNo);
		if (order == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(order);
	}

	@GetMapping("/order/startProduce/{orderId}")
	public Result<Order> modifyOrderStatusToProduceByOrderId_Feign(@PathVariable Integer orderId) {
		Order order = iOrderService.modifyOrderStatusToProduceByOrderId(orderId);
		if (order == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(order);
	}

	@GetMapping("/order/startDelivery/{orderId}/{deliveryTime}")
	public Result<Order> modifyOrderStatusToDeliveryByOrderId_Feign(@PathVariable Integer orderId,
	                                                               @PathVariable Long deliveryTime) {
		Order order = iOrderService.modifyOrderStatusToDeliveryByOrderId(orderId, deliveryTime);
		if (order == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(order);
	}

	@GetMapping("/orderByUserId/{userId}")
	public Result<List<Order>> getOrderByUserIdByPage(@PathVariable Integer userId, @RequestParam("page") Integer page,
	                                                  @RequestParam("size") Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
		List<Order> orderList = iOrderService.getOrderByUserIdByPage(userId, pageable);
		return Result.success(orderList, iOrderService.getOrderTotalNumberByUserId(userId).intValue());
	}

	@PostMapping("/order")
	public Result<Order> addOrder(@RequestBody @Valid OrderVO orderVO,
	                              BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(orderVO.toString());
		return Result.success(iOrderService.addOrder(orderVO));
	}

	@PutMapping("/order/{id}")
	public Result<Order> modifyOrderById(@PathVariable Integer id,
	                                     @RequestBody Order order) {
		Order orderRet = iOrderService.modifyOrderById(id, order);
		if (orderRet == null) return Result.failure(ResultCode.ORDER_NOT_EXIST);
		return Result.success(orderRet);
	}

	@DeleteMapping("/order/{id}")
	public Result<Order> deleteOrderById(@PathVariable Integer id) {
		Order order = iOrderService.deleteOrderById(id);
		if (order == null) return Result.failure(ResultCode.ORDER_NOT_EXIST);
		return Result.success(order);
	}
}
