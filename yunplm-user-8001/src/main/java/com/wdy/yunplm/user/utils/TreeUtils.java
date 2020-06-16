package com.wdy.yunplm.user.utils;


import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
	public static List<PermissionTree> transfer2Tree(List<Permission> allPermissions) {
		List<PermissionTree> permissionTrees = new ArrayList<>();
		transfer(0, allPermissions, permissionTrees);
		return permissionTrees;
	}

	private static void transfer(Integer id, List<Permission> allPermissions, List<PermissionTree> permissionTrees) {
		for (Permission per : allPermissions) {
			if (per.getParentId().equals(id)) {
				PermissionTree parent = new PermissionTree(per, new ArrayList<>());
				permissionTrees.add(parent);
				if (allPermissions.stream().anyMatch(p -> p.getParentId().equals(per.getId()))) {
					transfer(per.getId(), allPermissions, parent.getChildren());
				}
			}
		}
	}
}
