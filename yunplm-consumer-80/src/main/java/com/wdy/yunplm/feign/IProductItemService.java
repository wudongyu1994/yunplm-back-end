package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.ProductItem2PresentStep;
import com.wdy.yunplm.feign.impl.MyFallback;
import com.wdy.yunplm.vo.ProductItemVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(value = "productItem-server-8005", contextId = "from80to8005", fallback = MyFallback.class)
public interface IProductItemService {
	@GetMapping("/api/v1/productItem/{id}")
	Result<ProductItemVO> getProductItemVOById_Feign(@PathVariable Integer id);

	@GetMapping("/api/v1/order/{orderId}/productItem")
	Result<List<ProductItemVO>> getProductItemByOrderId_Feign(@PathVariable Integer orderId);

	@PostMapping("/api/v1/presentStep")
	Result<ProductItem2PresentStep> addPresentStep_Feign(@RequestBody ProductItem2PresentStep pi2ps);
}
