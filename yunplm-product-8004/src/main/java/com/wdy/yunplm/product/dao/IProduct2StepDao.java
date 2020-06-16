package com.wdy.yunplm.product.dao;

import com.wdy.yunplm.po.Product2Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProduct2StepDao extends JpaRepository<Product2Step, Integer> {
	List<Product2Step> findByProductId(Integer productId);
}
