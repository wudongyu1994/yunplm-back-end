package com.wdy.yunplm.logistics.service;


import com.wdy.yunplm.po.Logistics;

import java.util.List;

public interface ILogisticsService {
	List<Logistics> getLogisticsByExpressNo(String expressNo);

	Logistics addLogistics(Logistics logistics);

	Logistics modifyLogisticsById(Integer id, Logistics logistics);

	Logistics deleteLogisticsById(Integer id);
}
