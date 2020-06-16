package com.wdy.yunplm.vo;

import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString(callSuper = true)
public class RoleVO extends Role {
	protected List<Permission> permissionList;
	protected List<Integer> listPermissionId;
}
