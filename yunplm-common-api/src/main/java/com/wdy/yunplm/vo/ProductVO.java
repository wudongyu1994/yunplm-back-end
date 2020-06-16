package com.wdy.yunplm.vo;

import com.wdy.yunplm.po.Product;
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
public class ProductVO extends Product {
	protected List<Step> stepList;
	protected List<Integer> listStepId;
}
