package com.wdy.yunplm.logistics.service;


import com.wdy.yunplm.po.AfterSale;

import java.util.List;

public interface IAfterSaleService {
	List<AfterSale> getAfterSaleByProductItemId(Integer productItemId);

	AfterSale addAfterSale(AfterSale afterSale);
}
