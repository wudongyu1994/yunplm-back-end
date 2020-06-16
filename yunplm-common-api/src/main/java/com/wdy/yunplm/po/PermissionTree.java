package com.wdy.yunplm.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class PermissionTree extends Permission {
	protected List<PermissionTree> children;

	public PermissionTree(Permission permission, List<PermissionTree> children) {
		BeanUtils.copyProperties(permission,this);
		this.children = children;
	}
}
