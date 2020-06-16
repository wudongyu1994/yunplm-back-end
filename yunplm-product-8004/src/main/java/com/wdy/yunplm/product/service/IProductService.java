package com.wdy.yunplm.product.service;

import com.wdy.yunplm.po.Product;
import com.wdy.yunplm.vo.ProductVO;

import java.util.List;

public interface IProductService {
	List<ProductVO> getProduct();
	ProductVO addProductVO(ProductVO productVO);
	ProductVO modifyProductVOById(Integer id, ProductVO productVO);
	Product deleteProductById(Integer id);
}
