package com.wdy.yunplm.logistics.dao;

import com.wdy.yunplm.po.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogisticsDao extends JpaRepository<Logistics, Integer> {
	List<Logistics> findByExpressNo(String expressNo);
}
