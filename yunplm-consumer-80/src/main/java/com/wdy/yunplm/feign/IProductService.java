package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Product;
import com.wdy.yunplm.po.Step;
import com.wdy.yunplm.feign.impl.MyFallback;
import com.wdy.yunplm.vo.ProductVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "product-server-8004", fallback = MyFallback.class)
public interface IProductService {
	@GetMapping("/api/v1/product")
	Result<List<ProductVO>> getProductVO_Feign();

	@PostMapping("/api/v1/product")
	Result<ProductVO> addProductVO_Feign(@RequestBody ProductVO productVO);

	@PutMapping("/api/v1/product/{id}")
	Result<ProductVO> modifyProductVOById_Feign(@PathVariable Integer id, @RequestBody ProductVO product);

	@DeleteMapping("/api/v1/product/{id}")
	Result<Product> deleteProductById_Feign(@PathVariable Integer id);

	/******* step ********/
	@GetMapping("/api/v1/step")
	Result<List<Step>> getStep_Feign();

	@PostMapping("/api/v1/step")
	Result<Step> addStep_Feign(@RequestBody Step step);

	@PutMapping("/api/v1/step/{id}")
	Result<Step> modifyStepById_Feign(@PathVariable Integer id, @RequestBody Step step);

	@DeleteMapping("/api/v1/step/{id}")
	Result<Step> deleteStepById_Feign(@PathVariable Integer id);
}
