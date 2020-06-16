package com.wdy.yunplm.device.dao;

import com.wdy.yunplm.po.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeviceDao extends JpaRepository<Device, Integer> {
	List<Device> findByProductKeyAndDeviceName(String productKey, String deviceName);
	List<Device> findByUserId(Integer userId);
}
