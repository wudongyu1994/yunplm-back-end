package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.User;
import com.wdy.yunplm.feign.impl.MyFallback;
import com.wdy.yunplm.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "user-server-8001", contextId = "user", fallback = MyFallback.class)
public interface IUserService {

	@GetMapping("/api/v1/hello")
	Result<String> hello_Feign();

	@GetMapping("/api/v1/user")
	Result<List<UserVO>> getUserVOByPage_Feign(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

	@GetMapping("/api/v1/user/username/{username}")
	Result<User> getUserByUsername_Feign(@PathVariable String username);

	@PostMapping("/api/v1/user")
	Result<UserVO> addUserVO_Feign(@RequestBody UserVO userVO);

	@PutMapping("/api/v1/user/{id}")
	Result<UserVO> modifyUserVOById_Feign(@PathVariable Integer id, @RequestBody UserVO userVO);

	@PutMapping("/api/v1/user/{id}/password")
	Result<User> changeUserPasswordById_Feign(@PathVariable Integer id, @RequestParam String oldPassword,
	                                          @RequestParam String newPassword);

	@DeleteMapping("/api/v1/user/{id}")
	Result<User> deleteUserById_Feign(@PathVariable Integer id);
}
