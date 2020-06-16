package com.wdy.yunplm.product.dao;

import com.wdy.yunplm.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer> {
}
