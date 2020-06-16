package com.wdy.yunplm.vo;

import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ToString(callSuper = true)
public class UserVO extends User {
	protected List<Role> roleList;
	protected List<Integer> listRoleId;
}
