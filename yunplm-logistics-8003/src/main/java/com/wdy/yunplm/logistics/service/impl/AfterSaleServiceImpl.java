package com.wdy.yunplm.logistics.service.impl;

import com.wdy.yunplm.logistics.dao.IAfterSaleDao;
import com.wdy.yunplm.logistics.service.IAfterSaleService;
import com.wdy.yunplm.po.AfterSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AfterSaleServiceImpl implements IAfterSaleService {
	@Resource
	IAfterSaleDao iAfterSaleDao;

	@Override
	public AfterSale addAfterSale(AfterSale afterSale) {
		return iAfterSaleDao.saveAndFlush(afterSale);
	}

	@Override
	public List<AfterSale> getAfterSaleByProductItemId(Integer productItemId) {
		return iAfterSaleDao.findByProductItemId(productItemId);
	}
}
