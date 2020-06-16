package com.wdy.yunplm.productItem.service.impl;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.productItem.service.IOrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements IOrderService {
	@Override
	public Result<Order> modifyOrderStatusToProduceByOrderId_Feign(Integer orderId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}
}
