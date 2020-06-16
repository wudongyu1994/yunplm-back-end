package com.wdy.yunplm.logistics.dao;

import com.wdy.yunplm.po.AfterSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAfterSaleDao extends JpaRepository<AfterSale, Integer> {
	List<AfterSale> findByProductItemId(Integer productItemId);
}
