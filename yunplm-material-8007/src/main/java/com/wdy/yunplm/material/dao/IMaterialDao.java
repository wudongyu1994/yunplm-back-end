package com.wdy.yunplm.material.dao;

import com.wdy.yunplm.po.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMaterialDao extends JpaRepository<Material, Integer> {
	List<Material> findByProductItemId(Integer productItemId);
}
