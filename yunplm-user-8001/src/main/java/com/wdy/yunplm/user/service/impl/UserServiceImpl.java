package com.wdy.yunplm.user.service.impl;

import com.wdy.yunplm.base.BaseModel;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.po.Role2User;
import com.wdy.yunplm.po.User;
import com.wdy.yunplm.user.dao.IRole2UserDao;
import com.wdy.yunplm.user.dao.IUserDao;
import com.wdy.yunplm.user.service.IRoleService;
import com.wdy.yunplm.user.service.IUserService;
import com.wdy.yunplm.utils.BeanUtilsMy;
import com.wdy.yunplm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao iUserDao;
	@Resource
	private IRole2UserDao iRole2UserDao;
	@Resource
	private IRoleService iRoleService;

	@Override
	public List<UserVO> getUserVOByPage(Pageable pageable) {
		//get userList
		Page<User> userPage = iUserDao.findAll(pageable);
		List<User> userList = userPage.getContent();
		//new userVOList & add roles into userVOList
		List<UserVO> userVOList = new ArrayList<>();
		userList.forEach(user -> {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);
			List<Role> roleList = iRoleService.getRoleByUserId(user.getId());
			userVO.setRoleList(roleList);
			userVOList.add(userVO);
		});
		return userVOList;
	}

	@Override
	public Long getUserTotalNumber() {
		return iUserDao.count();
	}

	@Override
	public User getUserByUsername(String username) {
		return iUserDao.findByUsername(username);
	}

	@Override
	public UserVO addUserVO(UserVO userVO) {
		//if username has existed
		if (iUserDao.findByUsername(userVO.getUsername()) != null) return null;
		//add User to mysql
		userVO.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user = iUserDao.saveAndFlush(user);
		BeanUtils.copyProperties(user, userVO);

		//add Role2User to MYSql
		userVO.getListRoleId().forEach(roleId -> {
			iRole2UserDao.save(new Role2User(roleId, userVO.getId()));
		});
		iRole2UserDao.flush();
		//add returned roles to userVO, but we cannot read the new role2user because this mission hasn't commited
		List<Role> roleList = iRoleService.getRoleByUserId(userVO.getId());
		log.info(roleList.toString());
		userVO.setRoleList(roleList);
		return userVO;
	}

	@Override
	public UserVO modifyUserVOById(Integer id, UserVO userVO) {
		if (iUserDao.findById(id).isPresent()) {
			User oldUser = iUserDao.findById(id).get();
			Integer oldId = oldUser.getId();
			String oldPwd = oldUser.getPassword();
			BeanUtilsMy.copyPropertiesIgnoreNull(userVO, oldUser);
			oldUser.setId(oldId);
			oldUser.setPassword(oldPwd);
			User userRet = iUserDao.saveAndFlush(oldUser);
			BeanUtils.copyProperties(userRet, userVO);
//			log.info("userVO ==>" + userVO.toString());
			// modify roles
			List<Integer> listTemp = userVO.getListRoleId();
			List<Integer> newRoleIdList = listTemp == null ? new ArrayList<>() : listTemp;
//			log.info("newRoleIdList ==>" + newRoleIdList.toString());
			List<Role2User> oldR2UList = iRole2UserDao.findByUserId(id);
			List<Integer> addRoleIdList = newRoleIdList.isEmpty() ? new ArrayList<>() : newRoleIdList.stream()
					.filter(integer -> oldR2UList.stream().noneMatch(r2u -> r2u.getRoleId().equals(integer)))
					.collect(Collectors.toList());
			List<Integer> deleteR2UIdList = newRoleIdList.isEmpty() ? oldR2UList.stream()
					.map(BaseModel::getId)
					.collect(Collectors.toList())
					: oldR2UList.stream()
					.filter(role2User -> !newRoleIdList.contains(role2User.getRoleId()))
					.map(BaseModel::getId)
					.collect(Collectors.toList());
//			log.info("addRoleIdList==>" + addRoleIdList.toString());
//			log.info("deleteR2UIdList==>" + deleteR2UIdList.toString());
			if (!addRoleIdList.isEmpty()) {
				addRoleIdList.forEach(roleId -> iRole2UserDao.save(new Role2User(roleId, id)));
			}
			if (!deleteR2UIdList.isEmpty()) {
				deleteR2UIdList.forEach(iRole2UserDao::deleteById);
			}
			iRole2UserDao.flush();
			//get new roles
			List<Role> roleList = iRoleService.getRoleByUserId(id);
//			log.info("roleList==>" + roleList.toString());
			userVO.setRoleList(roleList);
			return userVO;
		} else {
			return null;
		}
	}

	@Override
	public User changeUserPasswordById(Integer id, String oldPassword, String newPassword) {
		if (iUserDao.findById(id).isPresent()) {
			User user = iUserDao.findById(id).get();
			if (new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
				user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
				return iUserDao.saveAndFlush(user);
			} else {
				return null;
			}
		} else return null;
	}

	@Override
	public User deleteUserById(Integer id) {
		// todo: delete Role2User record if exist
		if (iUserDao.findById(id).isPresent()) {
			User user = iUserDao.findById(id).get();
			iUserDao.deleteById(id);
			iUserDao.flush();
			return user;
		} else {
			return null;
		}
	}
}
