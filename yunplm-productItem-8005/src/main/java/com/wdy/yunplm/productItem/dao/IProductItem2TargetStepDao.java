package com.wdy.yunplm.productItem.dao;

import com.wdy.yunplm.po.ProductItem2TargetStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductItem2TargetStepDao extends JpaRepository<ProductItem2TargetStep, Integer> {
	List<ProductItem2TargetStep> findByProductItemId(Integer productItemId);
}
