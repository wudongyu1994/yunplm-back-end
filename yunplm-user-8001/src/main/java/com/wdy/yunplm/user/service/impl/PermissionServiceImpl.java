package com.wdy.yunplm.user.service.impl;

import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;
import com.wdy.yunplm.user.dao.IPermissionDao;
import com.wdy.yunplm.user.service.IPermissionService;
import com.wdy.yunplm.user.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	@Resource
	private IPermissionDao iPermissionDao;

	public List<PermissionTree> getAllPermissionTree() {
		return TreeUtils.transfer2Tree(iPermissionDao.findAll());
	}

	@Override
	public Long getPermissionTotalNumber() {
		return iPermissionDao.count();
	}

	@Override
	public List<Permission> getPermissionByUserId(Integer userId) {
		return iPermissionDao.findPermissionByUserId(userId);
	}

	@Override
	public Permission addPermission(Permission permission) {
		return iPermissionDao.saveAndFlush(permission);
	}

	@Override
	public Permission modifyPermissionById(Integer id, Permission permission) {
		if (iPermissionDao.findById(id).isPresent()) {
			Permission oldPer = iPermissionDao.findById(id).get();
			Integer oldId = oldPer.getId();
			BeanUtils.copyProperties(permission, oldPer);
			oldPer.setId(oldId);
			return iPermissionDao.saveAndFlush(oldPer);
		} else return null;
	}

	@Override
	public Permission deletePermissionById(Integer id) {
		if (iPermissionDao.findById(id).isPresent()) {
			Permission permission = iPermissionDao.findById(id).get();
			iPermissionDao.deleteById(id);
			iPermissionDao.flush();
			return permission;
		} else {
			return null;
		}
	}
}
