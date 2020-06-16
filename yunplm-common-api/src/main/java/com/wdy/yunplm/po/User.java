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
public class User extends BaseModel {
	@NotBlank
	@Column(nullable = false)
	protected String username;
	@NotBlank
	@Column(nullable = false)
	protected String password;
	protected String name;
	protected String corp;
	protected String phone;
	protected String email;
	protected Integer sex;
	@NotNull
	@Column(nullable = false)
	protected Integer status;

	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}
}
