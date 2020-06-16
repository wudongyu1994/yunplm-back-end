package com.wdy.yunplm.order.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.vo.OrderVO;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ProductItemServiceFallback implements IProductItemService {
	@Override
	public Result<ProductItem> addProductItem_Feign(@Valid OrderVO orderVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<ProductItem> getProductItemById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}
}
