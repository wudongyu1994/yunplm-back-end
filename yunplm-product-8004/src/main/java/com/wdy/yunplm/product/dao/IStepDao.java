package com.wdy.yunplm.product.dao;

import com.wdy.yunplm.po.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStepDao extends JpaRepository<Step, Integer> {
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT s  " +
			"FROM Product2Step p2s " +
			"INNER JOIN Step s ON s.id = p2s.stepId " +
			"WHERE p2s.productId = :productId")
	List<Step> findStepByProductId(@Param("productId") Integer productId);
}
