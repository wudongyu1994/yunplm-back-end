package com.wdy.yunplm.po;

import com.wdy.yunplm.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductItem2TargetStep extends BaseModel {
	public ProductItem2TargetStep(Integer productItemId, Integer targetStepId){
		this.createTime=new Date().getTime();
		this.updateTime=this.createTime;
		this.productItemId=productItemId;
		this.targetStepId=targetStepId;
	}
	@NotNull
	@Column(nullable = false)
	protected Integer productItemId;
	@NotNull
	@Column(nullable = false)
	protected Integer targetStepId;
}
