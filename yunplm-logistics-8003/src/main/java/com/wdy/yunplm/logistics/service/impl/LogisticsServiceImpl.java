package com.wdy.yunplm.logistics.service.impl;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.logistics.dao.ILogisticsDao;
import com.wdy.yunplm.logistics.service.ILogisticsService;
import com.wdy.yunplm.logistics.feign.IOrderService;
import com.wdy.yunplm.po.Logistics;
import com.wdy.yunplm.po.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class LogisticsServiceImpl implements ILogisticsService {
	@Resource
	private ILogisticsDao iLogisticsDao;
	@Resource
	private IOrderService iOrderService;

	@Override
	public List<Logistics> getLogisticsByExpressNo(String expressNo) {
		return iLogisticsDao.findByExpressNo(expressNo);
	}

	@Override
	public Logistics addLogistics(Logistics logistics) {
		// 1.get order, check if this expressNo is exist
		Result<Order> result = iOrderService.getOrderByExpressNo_Feign(logistics.getExpressNo());
		if (result.getCode() != 20000) {
			log.info("==> this expressNo has no order");
			return null;
		}
		// 2.add logistics to mysql
		Logistics logisticsRet = iLogisticsDao.saveAndFlush(logistics);
		log.info("logisticsRet ==> "+logisticsRet.toString());
		// 3.modify order status and updateTime
		result = iOrderService.modifyOrderStatusToDeliveryByOrderId_Feign(result.getData().getId(), logistics.getCreateTime());
		if (result.getCode() != 20000) {
			log.warn("==> change order status failed");
			log.warn("==> "+result.getMsg());
			return null;
		}
		return logisticsRet;
	}

	@Override
	public Logistics modifyLogisticsById(Integer id, Logistics logistics) {
		if (iLogisticsDao.findById(id).isPresent()) {
			Logistics oldLog = iLogisticsDao.findById(id).get();
			Integer oldId = oldLog.getId();
			BeanUtils.copyProperties(logistics, oldLog);
			oldLog.setId(oldId);
			return iLogisticsDao.saveAndFlush(oldLog);
		} else return null;
	}

	@Override
	public Logistics deleteLogisticsById(Integer id) {
		if (iLogisticsDao.findById(id).isPresent()) {
			Logistics logistics = iLogisticsDao.findById(id).get();
			iLogisticsDao.deleteById(id);
			iLogisticsDao.flush();
			return logistics;
		} else return null;
	}
}
