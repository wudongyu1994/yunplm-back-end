package com.wdy.yunplm.order.service.impl;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.order.dao.IOrderDao;
import com.wdy.yunplm.order.feign.IProductItemService;
import com.wdy.yunplm.order.service.IOrderService;
import com.wdy.yunplm.order.utils.OrderNoGener;
import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.utils.BeanUtilsMy;
import com.wdy.yunplm.vo.OrderStatus;
import com.wdy.yunplm.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements IOrderService {
	@Resource
	private IOrderDao iOrderDao;
	@Resource
	private IProductItemService iProductItemService;

	@Override
	public Order getOrderById(Integer orderId) {
		return iOrderDao.findById(orderId).orElse(null);
	}

	@Override
	public List<OrderStatus> getAllOderStatus() {
		return iOrderDao.tongjiOrderStatus();
	}

	@Override
	public List<OrderStatus> getOderStatusByUserId(Integer userId) {
		return iOrderDao.tongjiOrderStatusByUserId(userId);
	}

	@Override
	public List<Long> getOderIncome() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		long time0 = calendar.getTime().getTime();
		List<Long> list = new ArrayList<>();
		for (int i = 7; i > 0; i--) {
			Long res = iOrderDao.tongjiIncome(time0 - i * 24 * 3600 * 1000, time0 - (i - 1) * 24 * 3600 * 1000);
			list.add(res == null ? 0L : res);
		}
		return list;
	}

	@Override
	public Order getOrderByExpressNo(String expressNo) {
		if (iOrderDao.findByExpressNo(expressNo).isEmpty())
			return null;
		return iOrderDao.findByExpressNo(expressNo).get(0);
	}

	@Override
	public List<Order> getOrderByPage(Pageable pageable) {
		return iOrderDao.findAll(pageable).getContent();
	}

	@Override
	public List<Order> getOrderByUserIdByPage(Integer userId, Pageable pageable) {
		return iOrderDao.findByUserId(userId, pageable).getContent();
	}

	@Override
	public Long getOrderTotalNumber() {
		return iOrderDao.count();
	}

	@Override
	public Long getOrderTotalNumberByUserId(Integer userId) {
		return iOrderDao.countByUserId(userId);
	}

	@Override
	public Order addOrder(OrderVO orderVO) {
		Order targetOrder = new Order(orderVO);
		// get orderNo
		String orderNo = OrderNoGener.getInstance().GenerateOrder();
		targetOrder.setOrderStatus(Order.Status.CREATED);
		targetOrder.setOrderNo(orderNo);
		Order orderRet = iOrderDao.saveAndFlush(targetOrder);
		orderVO.setId(orderRet.getId());

		// 往productItem表中添加字段，以及添加productItem所对应的目标step
		Result<ProductItem> result = iProductItemService.addProductItem_Feign(orderVO);
		return orderRet;
	}

	@Override
	public Order modifyOrderStatusToProduceByOrderId(Integer orderId) {
		if (iOrderDao.findById(orderId).isPresent()) {
			Order order = iOrderDao.findById(orderId).get();
			if (order.getOrderStatus() < Order.Status.PRODUCE) {
				order.setOrderStatus(Order.Status.PRODUCE);
			}
			return order;
		} else return null;
	}

	@Override
	public Order modifyOrderStatusToDeliveryByOrderId(Integer orderId, Long deliveryTime) {
		if (iOrderDao.findById(orderId).isPresent()) {
			Order order = iOrderDao.findById(orderId).get();
			log.info("order before ==> "+order.toString());
			if (order.getDeliveryTime() == null) {
				order.setDeliveryTime(deliveryTime);
			}
			if (order.getOrderStatus() < Order.Status.DELIVERY) {
				order.setOrderStatus(Order.Status.DELIVERY);
			}
			log.info("order after ==> "+order.toString());
			return order;
		} else return null;
	}

	@Override
	public Order modifyOrderById(Integer id, Order order) {
		if (iOrderDao.findById(id).isPresent()) {
			Order oldOrder = iOrderDao.findById(id).get();
			Integer oldId = oldOrder.getId();
			BeanUtilsMy.copyPropertiesIgnoreNull(order, oldOrder);
			oldOrder.setId(oldId);
			return iOrderDao.saveAndFlush(oldOrder);
		} else return null;
	}

	@Override
	public Order deleteOrderById(Integer id) {
		if (iOrderDao.findById(id).isPresent()) {
			Order order = iOrderDao.findById(id).get();
			iOrderDao.deleteById(id);
			iOrderDao.flush();
			return order;
		} else return null;
	}
}
