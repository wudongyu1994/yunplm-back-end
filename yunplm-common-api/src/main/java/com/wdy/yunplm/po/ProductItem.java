package com.wdy.yunplm.po;

import com.wdy.yunplm.base.BaseModel;
import com.wdy.yunplm.vo.ProductItemVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductItem extends BaseModel {
	@NotBlank
	@Column(nullable = false)
	protected String name;
	@NotBlank
	@Column(nullable = false)
	protected String proNo;
	@NotBlank
	@Column(nullable = false)
	protected String serial;
	@NotNull
	@Column(nullable = false)
	protected Integer price;
	protected String description;
	//-------------------
	@NotNull
	@Column(nullable = false)
	protected Integer orderId;
	@NotNull
	@Column(nullable = false)
	protected Integer number;

	public ProductItem(ProductItemVO productItemVO){
		BeanUtils.copyProperties(productItemVO,this);
	}
}
