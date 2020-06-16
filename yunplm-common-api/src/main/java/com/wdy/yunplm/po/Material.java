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
public class Material extends BaseModel {
	@NotNull
	@Column(nullable = false)
	protected Integer productItemId;
	@NotBlank
	@Column(nullable = false)
	protected String name;
	@NotBlank
	@Column(nullable = false)
	protected String corp;
	@NotBlank
	@Column(nullable = false)
	protected String batchNo;
}