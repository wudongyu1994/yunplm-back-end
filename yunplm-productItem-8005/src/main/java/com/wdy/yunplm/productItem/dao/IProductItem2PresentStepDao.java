package com.wdy.yunplm.productItem.dao;

import com.wdy.yunplm.po.ProductItem2PresentStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductItem2PresentStepDao extends JpaRepository<ProductItem2PresentStep, Integer> {
	List<ProductItem2PresentStep> findByProductItemId(Integer productItemId);
}
