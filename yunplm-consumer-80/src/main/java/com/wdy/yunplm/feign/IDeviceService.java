package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.po.Device;
import com.wdy.yunplm.feign.impl.MyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "device-server-8006", fallback = MyFallback.class)
public interface IDeviceService {
	@GetMapping("/api/v1/device")
	Result<List<Device>> getAllDevice_Feign();

	@GetMapping("/api/v1/device/{userId}")
	Result<List<Device>> getDeviceByUserId_Feign(@PathVariable Integer userId);

	@GetMapping("/api/v1/device/{deviceId}/property")
	Result<Property> getDevicePropertyById_Feign(@PathVariable Integer deviceId);

	@GetMapping("/api/v1/device/{deviceId}/propertyHistory/led")
	Result<List<LedDot>> getDeviceLedHistoryById_Feign(@PathVariable Integer deviceId);

	@GetMapping("/api/v1/device/{deviceId}/propertyHistory/devTemp")
	Result<List<DevTempDot>> getDeviceDevTempHistoryById_Feign(@PathVariable Integer deviceId);

	@GetMapping("/api/v1/device/{deviceId}/event")
	Result<List<EventDot>> getDeviceEventById_Feign(@PathVariable Integer deviceId);

	@GetMapping("/api/v1/device/{deviceId}/eventClear")
	Result<String> clearDeviceEventById_Feign(@PathVariable Integer deviceId,
	                                          @RequestParam("clearTime") Long clearTime);

	@GetMapping("/api/v1/device/{deviceId}/propertyHistory/event")
	Result<List<EventDot>> getDeviceEventHistoryById_Feign(@PathVariable Integer deviceId);

	@GetMapping("/api/v1/device/{deviceId}/led")
	Result<String> setDeviceLedById_Feign(@PathVariable Integer deviceId,
	                                      @RequestParam("isTurnOn") Boolean isTurnOn);

	@PostMapping("/api/v1/device")
	Result<Device> addDevice_Feign(@RequestBody Device device);

	@DeleteMapping("/api/v1/device/{deviceId}")
	Result<Device> deleteDeviceById_Feign(@PathVariable Integer deviceId);
}
