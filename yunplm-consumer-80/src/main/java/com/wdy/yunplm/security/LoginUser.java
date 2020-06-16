package com.wdy.yunplm.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class LoginUser extends User implements UserDetails {

	private List<Permission> permissions;
	private List<Role> roles;
	private String token;
	/** 登陆时间戳（毫秒） */
	private Long loginTime;
	/** 过期时间戳 */
	private Long expireTime;

	//这里会获取当前登录用户的权限信息
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
//				.map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(permissions!=null) {
			for (Permission per : permissions) {
				authorities.add(new SimpleGrantedAuthority(per.getPermission()));
			}
		}
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {}

	// 账户是否未过期
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 账户是否未锁定
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return getStatus() != Status.LOCKED;
	}

	// 密码是否未过期
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 账户是否激活
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}

