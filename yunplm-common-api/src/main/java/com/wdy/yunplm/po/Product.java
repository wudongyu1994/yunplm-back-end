package com.wdy.yunplm.po;

import com.wdy.yunplm.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@ToString(callSuper = true)
public class Product extends BaseModel {
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
}
