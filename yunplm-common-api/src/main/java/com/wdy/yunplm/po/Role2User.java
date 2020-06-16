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
public class Role2User extends BaseModel {
	public Role2User(Integer roleId, Integer userId){
		this.createTime=new Date().getTime();
		this.updateTime=this.createTime;
		this.roleId=roleId;
		this.userId=userId;
	}

	@NotNull
	@Column(nullable = false)
	private Integer roleId;
	@NotNull
	@Column(nullable = false)
	private Integer userId;
}
