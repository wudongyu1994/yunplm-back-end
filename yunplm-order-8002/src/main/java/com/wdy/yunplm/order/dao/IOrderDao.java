package com.wdy.yunplm.order.dao;

import com.wdy.yunplm.po.Order;
import com.wdy.yunplm.vo.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDao extends JpaRepository<Order, Integer> {
	List<Order> findByExpressNo(String orderNo);

	Page<Order> findByUserId(Integer userId, Pageable pageable);

	Long countByUserId(Integer userId);

	@Query(value = "select new com.wdy.yunplm.vo.OrderStatus(o.orderStatus,count(o)) " +
			"from Order o " +
			"group by o.orderStatus")
	List<OrderStatus> tongjiOrderStatus();

	@Query(value = "select new com.wdy.yunplm.vo.OrderStatus(o.orderStatus,count(o)) " +
			"from Order o " +
			"where o.userId = ?1 " +
			"group by o.orderStatus ")
	List<OrderStatus> tongjiOrderStatusByUserId(Integer userId);

	@Query(value = "select sum(o.money) from Order o where o.orderStatus=4 and o.receiveTime>?1 and o.receiveTime<?2")
	Long tongjiIncome(Long from, Long to);

}
