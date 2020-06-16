package com.wdy.yunplm.logistics.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements IOrderService {
	@Override
	public Result<Order> getOrderByExpressNo_Feign(String expressNo) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> modifyOrderById_Feign(Integer id, Order order) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> modifyOrderStatusToDeliveryByOrderId_Feign(Integer orderId, Long deliveryTime) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}
}
