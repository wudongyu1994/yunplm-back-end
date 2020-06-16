package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
		import com.wdy.yunplm.feign.impl.MyFallback;
		import com.wdy.yunplm.po.AfterSale;
		import org.springframework.cloud.openfeign.FeignClient;
		import org.springframework.stereotype.Service;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.PathVariable;
		import org.springframework.web.bind.annotation.PostMapping;

		import java.util.List;

@Service
@FeignClient(value = "logistics-server-8003", contextId = "afterSale", fallback = MyFallback.class)
public interface IAfterSaleService {

	@GetMapping("/api/v1/afterSale/{productItemId}")
	Result<List<AfterSale>> getAfterSaleByProductItemId_Feign(@PathVariable Integer productItemId);

	@PostMapping("/api/v1/afterSale")
	Result<AfterSale> addAfterSale_Feign(AfterSale afterSale);
}
