package com.wdy.yunplm.productItem.dao;

import com.wdy.yunplm.po.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITargetStepDao extends JpaRepository<Step, Integer> {
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT s " +
			"FROM ProductItem2TargetStep pi2ts " +
			"INNER JOIN Step s ON s.id = pi2ts.targetStepId " +
			"WHERE pi2ts.productItemId = :productItemId ")
	List<Step> findTargetStepByProductItemId(@Param("productItemId") Integer productItemId);
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT s " +
			"FROM ProductItem2PresentStep pi2ps " +
			"INNER JOIN Step s ON s.id = pi2ps.presentStepId " +
			"WHERE pi2ps.productItemId = :productItemId ")
	List<Step> findPresentStepByProductItemId(@Param("productItemId") Integer productItemId);
}
