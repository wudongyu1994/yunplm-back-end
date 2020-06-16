package com.wdy.yunplm.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderIncome {
	private Long income7;   //7天前的收入
	private Long income6;
	private Long income5;
	private Long income4;
	private Long income3;
	private Long income2;
	private Long income1;
}
