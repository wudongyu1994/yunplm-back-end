package com.wdy.yunplm.vo;

import com.wdy.yunplm.po.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString(callSuper = true)
public class OrderVO extends Order {
	protected List<ProductItemVO> productItemVOList;
}
