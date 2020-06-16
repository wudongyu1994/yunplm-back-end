package com.wdy.yunplm.feign.impl;

import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.User;
import com.wdy.yunplm.security.LoginUser;
import com.wdy.yunplm.feign.IPermissionService;
import com.wdy.yunplm.feign.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * spring security登陆处理<br>
 */

@Service
@Slf4j
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Resource
	private IUserService iUserService;
	@Resource
	private IPermissionService iPermissionService;

	//根据用户名获取登录用户的完整信息
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = iUserService.getUserByUsername_Feign(username).getData();
		if (user == null) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		} else if (user.getStatus() == User.Status.LOCKED) {
			throw new LockedException("用户被锁定,请联系管理员");
		} else if (user.getStatus() == User.Status.DISABLED) {
			throw new DisabledException("用户已作废");
		}
//		log.info("user: " + user.toString());

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(user, loginUser);

		List<Permission> permissions = iPermissionService
				.getPermissionByUserId_Feign(user.getId()).getData();
		loginUser.setPermissions(permissions);

		return loginUser;
	}
}
