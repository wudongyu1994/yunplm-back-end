package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.feign.IAfterSaleService;
import com.wdy.yunplm.po.AfterSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class AfterSaleController extends ConsumerBaseApiController {
	@Resource
	IAfterSaleService iAfterSaleService;

	@GetMapping("/afterSale/{productItemId}")
	public Result<List<AfterSale>> getAfterSaleByProductItemId(@PathVariable Integer productItemId) {
		return iAfterSaleService.getAfterSaleByProductItemId_Feign(productItemId);
	}

	@PostMapping("/afterSale")
	public Result<AfterSale> addAfterSale(@RequestBody AfterSale afterSale){
		return iAfterSaleService.addAfterSale_Feign(afterSale);
	}

}
