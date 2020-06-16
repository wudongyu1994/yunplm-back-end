package com.wdy.yunplm.po;

import com.wdy.yunplm.base.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AfterSale extends BaseModel {
	@NotNull
	@Column(nullable = false)
	protected Integer productItemId;
	@NotNull
	@Column(nullable = false)
	protected Integer userId;
	@NotBlank
	@Column(nullable = false)
	protected String type;
	protected String description;
}
