package com.wdy.yunplm;

import com.wdy.yunplm.po.User;
import com.wdy.yunplm.user.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class UserTest {
	//	@Resource
//	IUserDao iUserDao;
//	@Autowired
//	RedisTemplate<String, String> redisTemplate;

	@Resource
	IUserDao iUserDao;
//	@Test
//	void testUser(){
//		User user=new User();
//		Long now=new Date().getTime();
//		user.setCreateTime(now);
//		user.setUpdateTime(now);
//		user.setName("zhangsan");
//		user.setUsername("admin");
//		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
//		user.setStatus(User.Status.VALID);
//		iUserDao.saveAndFlush(user);
//	}

//	@Test
//	void testObject(){
//		Tclass tclass=new Tclass();
//		if(tclass.user==null) log.info("tclass.user==null");
//	}
//	private class Tclass{
//		private User user;
//	}

	@Test
	void testRedis() {
//		ValueOperations<String, String> ops = redisTemplate.opsForValue();

//		ops.set("test", "hhh", 3, TimeUnit.MINUTES);
//		String ret = ops.get("test");
//		log.info("ret ==> " + ret);

//		User user =new User();
//		user.setId(1);
//		user.setStatus(1);
//		user.setUsername("test");
//		user.setPassword("test");
//		user.setName("test");
//		String userString = JSON.toJSONString(user);
//		ops.set("user",userString);
//		String retString =ops.get("user");
//		User userRet= JSON.parseObject(retString, User.class);
//		if(userRet!=null) log.info("userRet ==> "+userRet.toString());
//		else log.info("userRet==null");

//		List<User> userList = iUserDao.findAll();
//		ops.set("userList", JSON.toJSONString(userList));
//		List<User> ret=JSON.parseArray(ops.get("userList"),User.class);
//		log.info("ret ==> "+ret);

//		redisUtils.deleteByPattern("test*");

	}

	@Test
	void changePwd() {
		Integer id = 11;
		String newPassword = "1234";
		if (iUserDao.findById(id).isPresent()) {
			User user = iUserDao.findById(id).get();
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			User userRet = iUserDao.saveAndFlush(user);
			log.info("userRet ==> " + userRet.toString());
		}
	}
}

