package com.wdy.yunplm.order.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "productItem-server-8005", contextId = "from8002to8005", fallback = ProductItemServiceFallback.class)
public interface IProductItemService {
	@PostMapping("/api/v1/productItem")
	Result<ProductItem> addProductItem_Feign(@RequestBody OrderVO orderVO);

	@GetMapping("/api/v1/productItem/{id}")
	Result<ProductItem> getProductItemById_Feign(@PathVariable Integer id);
}
