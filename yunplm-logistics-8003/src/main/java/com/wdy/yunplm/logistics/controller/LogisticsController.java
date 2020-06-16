package com.wdy.yunplm.logistics.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Logistics;
import com.wdy.yunplm.logistics.service.ILogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class LogisticsController extends BaseApiController {
	@Resource
	ILogisticsService iLogisticsService;

	@GetMapping("/logistics/{expressNo}")
	public Result<List<Logistics>> getLogisticsByExpressNo(@PathVariable String expressNo) {
		List<Logistics> logisticsList = iLogisticsService.getLogisticsByExpressNo(expressNo);
		return Result.success(logisticsList, logisticsList.size());
	}

	@PostMapping("/logistics")
	public Result<Logistics> addLogistics(@RequestBody @Valid Logistics logistics,
	                                      BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info("logistics ==> "+logistics.toString());
		Logistics logisticsRet = iLogisticsService.addLogistics(logistics);
		if(logisticsRet==null) return Result.failure();
		return Result.success(logisticsRet);
	}

	@PutMapping("/logistics/{id}")
	public Result<Logistics> modifyLogisticsById(@PathVariable Integer id,
	                                             @RequestBody Logistics logistics) {
		Logistics logisticsRet = iLogisticsService.modifyLogisticsById(id, logistics);
		if (logisticsRet == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(logisticsRet);
	}

	@DeleteMapping("/logistics/{id}")
	public Result<Logistics> deleteLogisticsById(@PathVariable Integer id) {
		Logistics logistics = iLogisticsService.deleteLogisticsById(id);
		if (logistics == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(logistics);
	}

}
