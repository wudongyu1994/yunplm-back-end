package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.feign.IProductItemService;
import com.wdy.yunplm.po.ProductItem2PresentStep;
import com.wdy.yunplm.vo.ProductItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ProductItemController extends ConsumerBaseApiController {
	@Resource
	private IProductItemService iProductItemService;

	@GetMapping("/productItem/{id}")
	public Result<ProductItemVO> getProductItemVOById(@PathVariable Integer id){
		return iProductItemService.getProductItemVOById_Feign(id);
	}

	@GetMapping("/order/{orderId}/productItem")
	public Result<List<ProductItemVO>> getProductItemByOrderId(@PathVariable Integer orderId) {
		return iProductItemService.getProductItemByOrderId_Feign(orderId);
	}

	@PostMapping("/presentStep")
	public Result<ProductItem2PresentStep> addPresentStep(@RequestBody ProductItem2PresentStep pi2ps) {
		return iProductItemService.addPresentStep_Feign(pi2ps);
	}
}
