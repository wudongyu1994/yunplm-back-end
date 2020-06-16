package com.wdy.yunplm.user.service.impl;

import com.wdy.yunplm.base.BaseModel;
import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.po.Role2Permission;
import com.wdy.yunplm.user.dao.IPermissionDao;
import com.wdy.yunplm.user.dao.IRole2PermissionDao;
import com.wdy.yunplm.user.dao.IRoleDao;
import com.wdy.yunplm.user.service.IRoleService;
import com.wdy.yunplm.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Resource
	private IRoleDao iRoleDao;
	@Resource
	private IPermissionDao iPermissionDao;
	@Resource
	private IRole2PermissionDao iRole2PermissionDao;

	@Override
	public List<Role> getAllRole() {
		return iRoleDao.findAll();
	}

	@Override
	public List<RoleVO> getRoleVOByPage(Pageable pageable) {
		List<Role> roleList = iRoleDao.findAll(pageable).getContent();
		List<RoleVO> roleVOList = new ArrayList<>();
		roleList.forEach(role -> {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(role, roleVO);
			List<Permission> permissionList = iPermissionDao.findPermissionByRoleId(role.getId());
			roleVO.setPermissionList(permissionList);
			roleVOList.add(roleVO);
		});
		return roleVOList;
	}

	@Override
	public Long getRoleTotalNumber() {
		return iRoleDao.count();
	}

	@Override
	public List<Role> getRoleByUserId(Integer userId) {
		return iRoleDao.findRoleByUserId(userId);
	}

	@Override
	public RoleVO addRoleVO(RoleVO roleVO) {
		//add role to mysql
		Role role = new Role();
		BeanUtils.copyProperties(roleVO, role);
		role = iRoleDao.saveAndFlush(role);
		BeanUtils.copyProperties(role, roleVO);
		//add R2P to mysql
		roleVO.getListPermissionId().forEach(perId
				-> iRole2PermissionDao.save(new Role2Permission(perId, roleVO.getId())));
		iRole2PermissionDao.flush();
		// add returned permission to roleVO
		List<Permission> permissionList = iPermissionDao.findPermissionByRoleId(roleVO.getId());
		roleVO.setPermissionList(permissionList);
		return roleVO;
	}

	@Override
	public RoleVO modifyRoleVOById(Integer id, RoleVO roleVO) {
		if (iRoleDao.findById(id).isPresent()) {
			Role oldRole = iRoleDao.findById(id).get();
			Integer oldId = oldRole.getId();
			BeanUtils.copyProperties(roleVO, oldRole);
			oldRole.setId(oldId);
			Role roleRet = iRoleDao.saveAndFlush(oldRole);
			BeanUtils.copyProperties(roleRet, roleVO);
			// modify permissions
			List<Integer> listTemp = roleVO.getListPermissionId();
			List<Integer> newPerIdList = listTemp == null ? new ArrayList<>() : listTemp;
			log.info("newPerIdList ==>" + newPerIdList.toString());
			List<Role2Permission> oldR2PList = iRole2PermissionDao.findByRoleId(id);
			List<Integer> addPerIdList = newPerIdList.isEmpty() ? new ArrayList<>() : newPerIdList.stream()
					.filter(integer -> oldR2PList.stream().noneMatch(r2p -> r2p.getPermissionId().equals(integer)))
					.collect(Collectors.toList());
			List<Integer> deleteR2PIdList = newPerIdList.isEmpty() ? oldR2PList.stream()
					.map(BaseModel::getId)
					.collect(Collectors.toList())
					: oldR2PList.stream()
					.filter(role2Permission -> !newPerIdList.contains(role2Permission.getPermissionId()))
					.map(BaseModel::getId)
					.collect(Collectors.toList());
			log.info("addPerIdList ==>" + addPerIdList.toString());
			log.info("deleteR2PIdList ==>" + deleteR2PIdList.toString());
			if (!addPerIdList.isEmpty()) {
				addPerIdList.forEach(perId -> iRole2PermissionDao.save(new Role2Permission(perId, id)));
			}
			if (!deleteR2PIdList.isEmpty()) {
				deleteR2PIdList.forEach(iRole2PermissionDao::deleteById);
			}
			iRole2PermissionDao.flush();
			// get new pers
			List<Permission> permissionList = iPermissionDao.findPermissionByRoleId(id);
			log.info("permissionList ==>" + permissionList.toString());
			roleVO.setPermissionList(permissionList);
			return roleVO;
		} else {
			return null;
		}

	}

	@Override
	public Role deleteRoleById(Integer id) {
		//todo: if(there are some role binded to user or permission?
		if (iRoleDao.findById(id).isPresent()) {
			Role role = iRoleDao.findById(id).get();
			iRoleDao.deleteById(id);
			iRoleDao.flush();
			return role;
		} else {
			return null;
		}
	}
}
