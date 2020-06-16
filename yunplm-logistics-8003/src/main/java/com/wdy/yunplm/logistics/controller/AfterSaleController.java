package com.wdy.yunplm.logistics.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.logistics.service.IAfterSaleService;
import com.wdy.yunplm.po.AfterSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class AfterSaleController extends BaseApiController {
	@Resource
	IAfterSaleService iAfterSaleService;

	@GetMapping("/afterSale/{productItemId}")
	public Result<List<AfterSale>> getAfterSaleByProductItemId(@PathVariable Integer productItemId) {
		List<AfterSale> list = iAfterSaleService.getAfterSaleByProductItemId(productItemId);
		return Result.success(list, list.size());
	}

	@PostMapping("/afterSale")
	public Result<AfterSale> addAfterSale(@RequestBody @Valid AfterSale afterSale,
	                                  BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		return Result.success(iAfterSaleService.addAfterSale(afterSale));
	}
}
