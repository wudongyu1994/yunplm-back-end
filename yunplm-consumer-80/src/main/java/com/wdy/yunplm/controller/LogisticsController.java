package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Logistics;
import com.wdy.yunplm.feign.ILogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class LogisticsController extends ConsumerBaseApiController {
	@Resource
	private ILogisticsService iLogisticsService;

	@GetMapping("/logistics/{expressNo}")
	@PreAuthorize("hasAuthority('/logistics/get')")
	public Result<List<Logistics>> getLogisticsByExpressNo(@PathVariable String expressNo) {
		return iLogisticsService.getLogisticsByExpressNo_Feign(expressNo);
	}

	@PostMapping("/logistics")
	@PreAuthorize("hasAuthority('/logistics/add')")
	public Result<Logistics> addLogistics(@RequestBody @Valid Logistics logistics, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iLogisticsService.addLogistics_Feign(logistics);
	}

	@PutMapping("/logistics/{id}")
	@PreAuthorize("hasAuthority('/logistics/modify')")
	public Result<Logistics> modifyLogisticsById(@PathVariable Integer id, @RequestBody Logistics logistics) {
		return iLogisticsService.modifyLogisticsById_Feign(id, logistics);
	}

	@DeleteMapping("/logistics/{id}")
	@PreAuthorize("hasAuthority('/logistics/delete')")
	public Result<Logistics> deleteLogisticsById(@PathVariable Integer id) {
		return iLogisticsService.deleteLogisticsById_Feign(id);
	}
}
