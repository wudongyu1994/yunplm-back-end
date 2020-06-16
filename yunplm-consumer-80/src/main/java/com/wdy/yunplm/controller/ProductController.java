package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Product;
import com.wdy.yunplm.po.Step;
import com.wdy.yunplm.feign.IProductService;
import com.wdy.yunplm.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ProductController extends ConsumerBaseApiController {
	@Resource
	private IProductService iProductService;

	@GetMapping("/product")
	@PreAuthorize("hasAuthority('/product/get')")
	public Result<List<ProductVO>> getProductVO() {
		return iProductService.getProductVO_Feign();
	}

	@PostMapping("/product")
	@PreAuthorize("hasAuthority('/product/add')")
	public Result<ProductVO> addProductVO(@RequestBody @Valid ProductVO productVO,
	                                      BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iProductService.addProductVO_Feign(productVO);
	}

	@PutMapping("/product/{id}")
	@PreAuthorize("hasAuthority('/product/modify')")
	public Result<ProductVO> modifyProductVOById(@PathVariable Integer id, @RequestBody ProductVO productVO) {
		return iProductService.modifyProductVOById_Feign(id, productVO);
	}

	@DeleteMapping("/product/{id}")
	@PreAuthorize("hasAuthority('/product/delete')")
	public Result<Product> deleteProductById(@PathVariable Integer id) {
		return iProductService.deleteProductById_Feign(id);
	}

	/******* step ********/
	@GetMapping("/step")
	public Result<List<Step>> getStep() {
		return iProductService.getStep_Feign();
	}

	@PostMapping("/step")
	@PreAuthorize("hasAuthority('/step')")
	public Result<Step> addStep(@RequestBody @Valid Step step, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iProductService.addStep_Feign(step);
	}

	@PutMapping("/step/{id}")
	@PreAuthorize("hasAuthority('/step')")
	public Result<Step> modifyStepById(@PathVariable Integer id, @RequestBody Step step) {
		return iProductService.modifyStepById_Feign(id, step);
	}

	@DeleteMapping("/step/{id}")
	@PreAuthorize("hasAuthority('/step')")
	public Result<Step> deleteStepById(@PathVariable Integer id) {
		return iProductService.deleteStepById_Feign(id);
	}
}
