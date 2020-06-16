package com.wdy.yunplm.vo;

import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.po.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString(callSuper = true)
public class ProductItemVO extends ProductItem {
	protected List<Step> stepList;
	protected List<Integer> listStepId;
	protected List<Step> presentStepList;
	protected List<Integer> presentStepIdList;
}
