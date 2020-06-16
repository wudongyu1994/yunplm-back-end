package com.wdy.yunplm.user.service;

import com.wdy.yunplm.po.User;
import com.wdy.yunplm.vo.UserVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
	List<UserVO> getUserVOByPage(Pageable pageable);
	Long getUserTotalNumber();
	UserVO addUserVO(UserVO userVO);
	UserVO modifyUserVOById(Integer id, UserVO userVO);
	User deleteUserById(Integer id);

	User changeUserPasswordById(Integer id, String oldPassword, String newPassword);

	User getUserByUsername(String username);
}
