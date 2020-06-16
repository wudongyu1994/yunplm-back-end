package com.wdy.yunplm.productItem.service;


import com.wdy.yunplm.po.ProductItem2PresentStep;
import com.wdy.yunplm.vo.OrderVO;
import com.wdy.yunplm.vo.ProductItemVO;

import java.util.List;

public interface IProductItemService {
	List<ProductItemVO> getProductItemVOByOrderId(Integer orderId);

	Integer addProductItem(OrderVO orderVO);
	ProductItem2PresentStep addPresentStep(ProductItem2PresentStep pi2ps);

	ProductItemVO getProductItemVOById(Integer id);
//	ProductItem addProductItem(ProductItem productItem);
//	ProductItem modifyProductItemById(Integer id, ProductItem productItem);
//
//	ProductItem deleteProductItemById(Integer id);
}
