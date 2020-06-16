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
public class Logistics extends BaseModel {
	@NotBlank
	@Column(nullable = false)
	protected String address;
	@NotBlank
	@Column(nullable = false)
	protected String expressNo;
	@NotNull
	@Column(nullable = false)
	protected Integer userId;
}
