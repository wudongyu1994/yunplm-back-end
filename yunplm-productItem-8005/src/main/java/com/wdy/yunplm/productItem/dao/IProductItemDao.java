package com.wdy.yunplm.productItem.dao;

import com.wdy.yunplm.po.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductItemDao extends JpaRepository<ProductItem, Integer> {
	List<ProductItem> findByOrderId(Integer orderId);
}
