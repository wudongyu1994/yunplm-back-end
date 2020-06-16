package com.wdy.yunplm.productItem.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.po.ProductItem2PresentStep;
import com.wdy.yunplm.productItem.service.IProductItemService;
import com.wdy.yunplm.vo.OrderVO;
import com.wdy.yunplm.vo.ProductItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ProductItemController extends BaseApiController {
	@Resource
	private IProductItemService iProductItemService;

	@GetMapping("/productItem/{id}")
	public Result<ProductItemVO> getProductItemVOById(@PathVariable Integer id) {
		ProductItemVO productItemVO = iProductItemService.getProductItemVOById(id);
		if (productItemVO == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(productItemVO);
	}

	@GetMapping("/order/{orderId}/productItem")
	public Result<List<ProductItemVO>> getProductItemByOrderId(@PathVariable Integer orderId) {
		List<ProductItemVO> productItemVOList = iProductItemService.getProductItemVOByOrderId(orderId);
		return Result.success(productItemVOList, productItemVOList.size());
	}

	@PostMapping("/productItem")
	public Result<ProductItem> addProductItem_Feign(@RequestBody @Valid OrderVO orderVO,
	                                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
//		log.info("ready to add productItem, orderVO ==> " + orderVO.toString());
		Integer code = iProductItemService.addProductItem(orderVO);
		if (code == 0) return Result.success();
		else return Result.failure();
	}

	@PostMapping("/presentStep")
	public Result<ProductItem2PresentStep> addPresentStep(@RequestBody ProductItem2PresentStep pi2ps) {
		log.info(pi2ps.toString());
		ProductItem2PresentStep pi2psRet = iProductItemService.addPresentStep(pi2ps);
		if (pi2psRet == null) return Result.failure("该产品不存在、该产品没有对应的订单、该步骤不存在或该步骤已完成");
		return Result.success(pi2psRet);
	}

//	@PutMapping("/productItem/{id}")
//	public Result<ProductItem> modifyProductItemById(@PathVariable Integer id,
//	                                                 @RequestBody ProductItem productItem) {
//		ProductItem productItemRet = iProductItemService.modifyProductItemById(id, productItem);
//		if (productItemRet == null) return Result.failure(ResultCode.ORDER_NOT_EXIST);
//		return Result.success(productItemRet);
//	}
//
//	@DeleteMapping("/productItem/{id}")
//	public Result<ProductItem> deleteProductItemById(@PathVariable Integer id) {
//		ProductItem productItem = iProductItemService.deleteProductItemById(id);
//		if (productItem == null) return Result.failure(ResultCode.NOT_EXIST);
//		return Result.success(productItem);
//	}

}
