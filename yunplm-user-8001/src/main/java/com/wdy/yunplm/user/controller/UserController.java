package com.wdy.yunplm.user.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.User;
import com.wdy.yunplm.user.service.IUserService;
import com.wdy.yunplm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
//@PreAuthorize("hasAuthority('/user')")
public class UserController extends BaseApiController {
	@Resource
	private IUserService userService;

	@GetMapping("/hello")
	public Result<String> hello() {
		return Result.success("hello");
	}

	@GetMapping("/user")
	public Result<List<UserVO>> getUserVOByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
		List<UserVO> userList = userService.getUserVOByPage(pageable);
		return Result.success(userList, userService.getUserTotalNumber().intValue());
	}

	@GetMapping("/user/username/{username}")
	public Result<User> getUserByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
//		log.info("user ==> "+user.toString());
		if (user == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(user);
	}

	@PostMapping("/user")
//	@PreAuthorize("hasAuthority('/user/add')")
	public Result<UserVO> addUserVO(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(userVO.toString());
		UserVO userVORet=userService.addUserVO(userVO);
		if(userVORet==null) return Result.failure(ResultCode.USERNAME_REPEAT);
		return Result.success(userVORet);
	}

	@PutMapping("/user/{id}")
//	@PreAuthorize("hasAuthority('/user/modify')")
	public Result<UserVO> modifyUserVOById(@PathVariable Integer id, @RequestBody UserVO userVO) {
		UserVO userVORet = userService.modifyUserVOById(id, userVO);
		if (userVORet == null) return Result.failure("没有该用户");
		else return Result.success(userVORet);
	}

	@PutMapping("/user/{id}/password")
	public Result<User> changeUserPasswordById(@PathVariable Integer id, @RequestParam String oldPassword,
	                                           @RequestParam String newPassword) {
		User userRet = userService.changeUserPasswordById(id, oldPassword, newPassword);
		if (userRet == null) return Result.failure("旧密码错误");
		else return Result.success(userRet);
	}

	@DeleteMapping("/user/{id}")
//	@PreAuthorize("hasAuthority('/user/delete')")
	public Result<User> deleteUserById(@PathVariable Integer id) {
		User user = userService.deleteUserById(id);
		if (user == null) {
			return Result.failure();
		}
		return Result.success();
	}
}
