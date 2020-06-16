package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Logistics;
import com.wdy.yunplm.feign.impl.MyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "logistics-server-8003", contextId = "logistics", fallback = MyFallback.class)
public interface ILogisticsService {
	@GetMapping("/api/v1/logistics/{expressNo}")
	Result<List<Logistics>> getLogisticsByExpressNo_Feign(@PathVariable String expressNo);

	@PostMapping("/api/v1/logistics")
	Result<Logistics> addLogistics_Feign(@RequestBody Logistics logistics);

	@PutMapping("/api/v1/logistics/{id}")
	Result<Logistics> modifyLogisticsById_Feign(@PathVariable Integer id, @RequestBody Logistics logistics);

	@DeleteMapping("/api/v1/logistics/{id}")
	Result<Logistics> deleteLogisticsById_Feign(@PathVariable Integer id);
}
