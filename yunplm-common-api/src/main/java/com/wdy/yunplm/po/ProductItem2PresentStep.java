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
public class ProductItem2PresentStep extends BaseModel {
	public ProductItem2PresentStep(Integer productItemId, Integer presentStepId){
		this.createTime=new Date().getTime();
		this.updateTime=this.createTime;
		this.productItemId=productItemId;
		this.presentStepId=presentStepId;
	}

	@NotNull
	@Column(nullable = false)
	protected Integer productItemId;
	@NotNull
	@Column(nullable = false)
	protected Integer presentStepId;
}
