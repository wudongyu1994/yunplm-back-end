package com.wdy.yunplm.device.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.device.service.IDeviceService;
import com.wdy.yunplm.po.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class DeviceController extends BaseApiController {
	@Resource
	private IDeviceService iDeviceService;

	@GetMapping("/device")
	public Result<List<Device>> getAllDevice() {
		List<Device> deviceList = iDeviceService.getAllDevice();
		return Result.success(deviceList,deviceList.size());
	}

	@GetMapping("/device/{userId}")
	public Result<List<Device>> getDeviceByUserId(@PathVariable Integer userId) {
		List<Device> deviceList = iDeviceService.getDeviceByUserId(userId);
		return Result.success(deviceList,deviceList.size());
	}

	@GetMapping("/device/{deviceId}/property")
	public Result<Property> getDevicePropertyById(@PathVariable Integer deviceId) {
		Property property = iDeviceService.getDevicePropertyById(deviceId);
		if(property==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(property);
	}

	@GetMapping("/device/{deviceId}/propertyHistory/led")
	public Result<List<LedDot>> getDeviceLedHistoryById(@PathVariable Integer deviceId) {
		List<LedDot> ledDotList = iDeviceService.getDeviceLedHistoryById(deviceId);
		if(ledDotList==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(ledDotList,ledDotList.size());
	}

	@GetMapping("/device/{deviceId}/propertyHistory/devTemp")
	public Result<List<DevTempDot>> getDeviceDevTempHistoryById(@PathVariable Integer deviceId) {
		List<DevTempDot> devTempDotList = iDeviceService.getDeviceDevTempHistoryById(deviceId);
		if(devTempDotList==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(devTempDotList,devTempDotList.size());
	}

	@GetMapping("/device/{deviceId}/event")
	public Result<List<EventDot>> getDeviceEventById(@PathVariable Integer deviceId) {
		List<EventDot> eventDotList = iDeviceService.getDeviceEventById(deviceId);
		if(eventDotList==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(eventDotList,eventDotList.size());
	}

	@GetMapping("/device/{deviceId}/eventClear")
	public Result<String> clearDeviceEventById(@PathVariable Integer deviceId,
	                                           @RequestParam("clearTime") Long clearTime) {
		String ret = iDeviceService.clearDeviceEventById(deviceId,clearTime);
		if(ret==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(ret);
	}

	@GetMapping("/device/{deviceId}/propertyHistory/event")
	public Result<List<EventDot>> getDeviceEventHistoryById(@PathVariable Integer deviceId) {
		List<EventDot> eventDotList = iDeviceService.getDeviceEventHistoryById(deviceId);
		if(eventDotList==null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(eventDotList,eventDotList.size());
	}

	@GetMapping("/device/{deviceId}/led")
	public Result<String> setDeviceLedById(@PathVariable Integer deviceId,
	                                       @RequestParam("isTurnOn") Boolean isTurnOn) {
		String ret = iDeviceService.setLedStatus(deviceId, isTurnOn);
		if(ret==null) return Result.failure(ResultCode.NOT_EXIST);
		else return Result.success(ret);
	}

	@PostMapping("/device")
	public Result<Device> addDevice(@RequestBody @Valid Device device,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(device.toString());
		Device deviceRet = iDeviceService.addDevice(device);
		if (deviceRet == null) {
			return Result.failure(ResultCode.REPEAT);
		} else {
			return Result.success(deviceRet);
		}
	}

	@DeleteMapping("/device/{deviceId}")
	public Result<Device> deleteDeviceById(@PathVariable Integer deviceId) {
		Device device = iDeviceService.deleteDeviceById(deviceId);
		if (device == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(device);
	}

}
