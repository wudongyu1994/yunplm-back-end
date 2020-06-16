package com.wdy.yunplm.product.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Product;
import com.wdy.yunplm.po.Step;
import com.wdy.yunplm.product.service.IProductService;
import com.wdy.yunplm.product.service.IStepService;
import com.wdy.yunplm.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
//@PreAuthorize("hasAuthority('/permission')")
public class ProductController extends BaseApiController {
	@Resource
	private IProductService iProductService;
	@Resource
	private IStepService iStepService;

	@GetMapping("/product")
	public Result<List<ProductVO>> getProductVO() {
		List<ProductVO> productVOList = iProductService.getProduct();
		if(productVOList==null) return Result.failure();
		return Result.success(productVOList, productVOList.size());
	}

	@PostMapping("/product")
	public Result<ProductVO> addProductVO(@RequestBody @Valid ProductVO productVO,
	                                      BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(productVO.toString());
		return Result.success(iProductService.addProductVO(productVO));
	}

	@PutMapping("/product/{id}")
	public Result<ProductVO> modifyProductVOById(@PathVariable Integer id,
	                                             @RequestBody ProductVO productVO) {
		ProductVO productVORet = iProductService.modifyProductVOById(id, productVO);
		if (productVORet == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(productVORet);
	}

	@DeleteMapping("/product/{id}")
	public Result<Product> deleteProductById(@PathVariable Integer id) {
		Product product = iProductService.deleteProductById(id);
		if (product == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(product);
	}

	/******* step ********/
	@GetMapping("/step")
	public Result<List<Step>> getStep() {
		List<Step> stepList = iStepService.getAllStep();
		return Result.success(stepList, stepList.size());
	}

	@PostMapping("/step")
	public Result<Step> addStep(@RequestBody @Valid Step step, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(step.toString());
		return Result.success(iStepService.addStep(step));
	}

	@PutMapping("/step/{id}")
	public Result<Step> modifyStepById(@PathVariable Integer id, @RequestBody Step step) {
		Step stepRet = iStepService.modifyStepById(id, step);
		if (stepRet == null) return Result.failure(ResultCode.ORDER_NOT_EXIST);
		return Result.success(stepRet);
	}

	@DeleteMapping("/step/{id}")
	public Result<Step> deleteStepById(@PathVariable Integer id) {
		Step step = iStepService.deleteStepById(id);
		if (step == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(step);
	}
}
