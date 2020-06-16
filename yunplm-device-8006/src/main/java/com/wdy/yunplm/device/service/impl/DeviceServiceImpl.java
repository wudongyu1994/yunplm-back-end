package com.wdy.yunplm.device.service.impl;

import com.aliyuncs.iot.model.v20180120.GetDeviceStatusResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceEventDataResponse;
import com.aliyuncs.iot.model.v20180120.QueryDevicePropertyDataResponse;
import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.device.dao.IDeviceDao;
import com.wdy.yunplm.device.iot.DeviceManager;
import com.wdy.yunplm.device.iot.ThingModelManager;
import com.wdy.yunplm.device.service.IDeviceService;
import com.wdy.yunplm.po.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class DeviceServiceImpl implements IDeviceService {
	@Resource
	private IDeviceDao iDeviceDao;
	private Long endTime;
	private Long startTime;


	private void getTime() {
		endTime = new Date().getTime();
		startTime = endTime - 5 * 24 * 3600 * 1000;
	}

	@Override
	public List<Device> getAllDevice() {
		return iDeviceDao.findAll();
	}

	@Override
	public List<Device> getDeviceByUserId(Integer userId) {
		return iDeviceDao.findByUserId(userId);
	}

	@Override
	public Property getDevicePropertyById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			Property property = new Property();
			Device device = iDeviceDao.findById(deviceId).get();
			getTime();
			GetDeviceStatusResponse.Data statusData = DeviceManager
					.getDeviceStatus(device.getProductKey(), device.getDeviceName(), "");
			QueryDevicePropertyDataResponse.Data ledData =
					ThingModelManager.queryDevicePropertyData("", device.getProductKey(),
							device.getDeviceName(), "is_led_on", startTime, endTime, 1, 0);
			QueryDevicePropertyDataResponse.Data devTempData =
					ThingModelManager.queryDevicePropertyData("", device.getProductKey(),
							device.getDeviceName(), "dev_temp", startTime, endTime, 1, 0);
			property.setStatus((statusData == null) ? "" : statusData.getStatus());
			property.setIsLedOn(ledData != null && !ledData.getList().isEmpty() && Integer.parseInt(ledData.getList().get(0).getValue()) > 0);
			property.setDevTemp(devTempData == null || devTempData.getList().isEmpty() ? 0f : Float.parseFloat(devTempData.getList().get(0).getValue()));
			return property;
		}
		return null;
	}

	@Override
	public List<LedDot> getDeviceLedHistoryById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			List<LedDot> ledDotList = new ArrayList<>();
			Device device = iDeviceDao.findById(deviceId).get();
			getTime();
			QueryDevicePropertyDataResponse.Data ledData =
					ThingModelManager.queryDevicePropertyData("", device.getProductKey(),
							device.getDeviceName(), "is_led_on", startTime, endTime, 10, 0);
			if (ledData == null || ledData.getList().isEmpty()) {
				return ledDotList;
			} else {
				return ledData.getList().stream()
						.map(temp -> new LedDot(Integer.parseInt(temp.getValue()) > 0, Long.parseLong(temp.getTime())))
						.collect(Collectors.toList());
			}
		}
		return null;
	}

	@Override
	public List<DevTempDot> getDeviceDevTempHistoryById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			List<DevTempDot> devTempDotList = new ArrayList<>();
			Device device = iDeviceDao.findById(deviceId).get();
			getTime();
			QueryDevicePropertyDataResponse.Data devTempData =
					ThingModelManager.queryDevicePropertyData("", device.getProductKey(),
							device.getDeviceName(), "dev_temp", startTime, endTime, 10, 0);
			if (devTempData == null || devTempData.getList().isEmpty()) {
				return devTempDotList;
			} else {
				return devTempData.getList().stream()
						.map(temp -> new DevTempDot(Float.parseFloat(temp.getValue()), Long.parseLong(temp.getTime())))
						.collect(Collectors.toList());
			}
		}
		return null;
	}

	@Override
	public Device addDevice(Device device) {
		// 检查该设备是否已经添加到数据库了
		List<Device> deviceList = iDeviceDao.findByProductKeyAndDeviceName(device.getProductKey(), device.getDeviceName());
		if (!deviceList.isEmpty()) { // 如果队列不为空，说明已经添加过了
			return null;
		} else { // 如果队列为空，则进行添加操作
			return iDeviceDao.saveAndFlush(device);
		}
	}

	@Override
	public List<EventDot> getDeviceEventById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			List<EventDot> ret = new ArrayList<>();
			Long lastEventTime = iDeviceDao.findById(deviceId).get().getLastEventTime();
			List<EventDot> eventDotList = getDeviceEventHistoryById(deviceId);
			if (eventDotList != null && !eventDotList.isEmpty()) {
				for (EventDot eventDot : eventDotList) {
					if (eventDot.getTime() <= lastEventTime) break;
					ret.add(eventDot);
				}
			}
			return ret;
		}
		return null;
	}

	@Override
	public String clearDeviceEventById(Integer deviceId, Long clearTime) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			Device oldDevice = iDeviceDao.findById(deviceId).get();
			oldDevice.setLastEventTime(clearTime);
			iDeviceDao.saveAndFlush(oldDevice);
			return "clear Event ok!";
		}
		return null;
	}

	@Override
	public List<EventDot> getDeviceEventHistoryById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			List<EventDot> eventDotList = new ArrayList<>();
			Device device = iDeviceDao.findById(deviceId).get();
			getTime();
			QueryDeviceEventDataResponse.Data eventData =
					ThingModelManager.queryDeviceEventData("", device.getProductKey(),
							device.getDeviceName(), "error", "is_error",
							startTime, endTime, 10, 0);
			if (eventData != null && !eventData.getList().isEmpty()) {

				eventDotList = eventData.getList().stream()
						.map(temp -> {
//							log.info("errorCode ==> " + temp.getOutputData().substring(14, 15));//{"error_code" :1}
							Integer errorCode = Integer.parseInt(temp.getOutputData().substring(14, 15));
							return new EventDot(errorCode, Long.parseLong(temp.getTime()));
						})
						.collect(Collectors.toList());
			}
			return eventDotList;
		}
		return null;
	}

	@Override
	public String setLedStatus(Integer deviceId, Boolean isTurnOn) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			Device device = iDeviceDao.findById(deviceId).get();
			getTime();
			ThingModelManager.setDeviceProperty("", device.getProductKey(),
					device.getDeviceName(), isTurnOn ? "{\"is_led_on\":1}" : "{\"is_led_on\":0}");
			return "setDeviceProperty sent!";
		}
		return null;
	}

	@Override
	public Device deleteDeviceById(Integer deviceId) {
		if (iDeviceDao.findById(deviceId).isPresent()) {
			Device device = iDeviceDao.findById(deviceId).get();
			iDeviceDao.deleteById(deviceId);
			iDeviceDao.flush();
			return device;
		} else {
			return null;
		}
	}
}
