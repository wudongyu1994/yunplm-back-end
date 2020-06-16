package com.wdy.yunplm.order.service;

import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.vo.OrderStatus;
import com.wdy.yunplm.vo.OrderVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
	Order getOrderByExpressNo(String expressNo);

	List<Order> getOrderByPage(Pageable pageable);

	List<Order> getOrderByUserIdByPage(Integer userId, Pageable pageable);

	Long getOrderTotalNumber();

	Long getOrderTotalNumberByUserId(Integer userId);

	Order addOrder(OrderVO orderVO);

	Order modifyOrderById(Integer id, Order order);

	Order deleteOrderById(Integer id);

	Order modifyOrderStatusToProduceByOrderId(Integer orderId);

	Order modifyOrderStatusToDeliveryByOrderId(Integer orderId, Long deliveryTime);

	List<OrderStatus> getAllOderStatus();

	List<OrderStatus> getOderStatusByUserId(Integer userId);

	List<Long> getOderIncome();

	Order getOrderById(Integer orderId);
}
