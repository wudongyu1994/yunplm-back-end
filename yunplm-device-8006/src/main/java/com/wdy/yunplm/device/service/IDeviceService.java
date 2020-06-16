package com.wdy.yunplm.device.service;


import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.po.Device;

import java.util.List;

public interface IDeviceService {
	List<Device> getAllDevice();
	List<Device> getDeviceByUserId(Integer userId);
	Property getDevicePropertyById(Integer deviceId);
	List<LedDot> getDeviceLedHistoryById(Integer deviceId);
	List<DevTempDot> getDeviceDevTempHistoryById(Integer deviceId);
	List<EventDot> getDeviceEventHistoryById(Integer deviceId);
	Device addDevice(Device device);
	Device deleteDeviceById(Integer deviceId);
	String setLedStatus(Integer deviceId, Boolean isTurnOn);
	List<EventDot> getDeviceEventById(Integer deviceId);
	String clearDeviceEventById(Integer deviceId, Long clearTime);
}
