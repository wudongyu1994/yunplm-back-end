package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.po.Device;
import com.wdy.yunplm.feign.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class DeviceController extends ConsumerBaseApiController {
	@Resource
	private IDeviceService iDeviceService;

	@GetMapping("/device")
	@PreAuthorize("hasAuthority('/device/admin')")
	public Result<List<Device>> getAllDevice() {
		return iDeviceService.getAllDevice_Feign();
	}

	@GetMapping("/device/{userId}")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<List<Device>> getDeviceByUserId(@PathVariable Integer userId) {
		return iDeviceService.getDeviceByUserId_Feign(userId);
	}

	@GetMapping("/device/{deviceId}/property")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<Property> getDevicePropertyById(@PathVariable Integer deviceId) {
		return iDeviceService.getDevicePropertyById_Feign(deviceId);
	}

	@GetMapping("/device/{deviceId}/propertyHistory/led")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<List<LedDot>> getDeviceLedHistoryById(@PathVariable Integer deviceId) {
		return iDeviceService.getDeviceLedHistoryById_Feign(deviceId);
	}

	@GetMapping("/device/{deviceId}/propertyHistory/devTemp")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<List<DevTempDot>> getDeviceDevTempHistoryById(@PathVariable Integer deviceId) {
		return iDeviceService.getDeviceDevTempHistoryById_Feign(deviceId);
	}

	@GetMapping("/device/{deviceId}/event")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<List<EventDot>> getDeviceEventById(@PathVariable Integer deviceId) {
		return iDeviceService.getDeviceEventById_Feign(deviceId);
	}

	@GetMapping("/device/{deviceId}/eventClear")
	@PreAuthorize("hasAuthority('/device/modify')")
	public Result<String> clearDeviceEventById(@PathVariable Integer deviceId,
	                                           @RequestParam("clearTime") Long clearTime) {
		return iDeviceService.clearDeviceEventById_Feign(deviceId, clearTime);
	}

	@GetMapping("/device/{deviceId}/propertyHistory/event")
	@PreAuthorize("hasAuthority('/device/get')")
	public Result<List<EventDot>> getDeviceEventHistoryById(@PathVariable Integer deviceId) {
		return iDeviceService.getDeviceEventHistoryById_Feign(deviceId);
	}

	@GetMapping("/device/{deviceId}/led")
	@PreAuthorize("hasAuthority('/device/modify')")
	public Result<String> setDeviceLedById(@PathVariable Integer deviceId,
	                                       @RequestParam("isTurnOn") Boolean isTurnOn) {
		return iDeviceService.setDeviceLedById_Feign(deviceId, isTurnOn);
	}

	@PostMapping("/device")
	@PreAuthorize("hasAuthority('/device/add')")
	public Result<Device> addDevice(@RequestBody @Valid Device device,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iDeviceService.addDevice_Feign(device);
	}

	@DeleteMapping("/device/{deviceId}")
	@PreAuthorize("hasAuthority('/device/delete')")
	public Result<Device> deleteDeviceById(@PathVariable Integer deviceId) {
		return iDeviceService.deleteDeviceById_Feign(deviceId);
	}
}
