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
public class Role2Permission extends BaseModel {
	public Role2Permission(Integer permissionId, Integer roleId){
		this.createTime=new Date().getTime();
		this.updateTime=this.createTime;
		this.roleId=roleId;
		this.permissionId=permissionId;
	}
	@NotNull
	@Column(nullable = false)
	private Integer roleId;
	@NotNull
	@Column(nullable = false)
	private Integer permissionId;
}
