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
@ToString(callSuper = true)
@NoArgsConstructor
public class Product2Step extends BaseModel {
	public Product2Step(Integer productId, Integer stepId){
		this.createTime=new Date().getTime();
		this.updateTime=this.createTime;
		this.productId=productId;
		this.stepId=stepId;
	}
	@NotNull
	@Column(nullable = false)
	protected Integer productId;
	@NotNull
	@Column(nullable = false)
	protected Integer stepId;
}
