package yunplm;

import com.wdy.yunplm.Consumer_App_80;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Consumer_App_80.class)
@Slf4j
public class ConsumerTest {

//	@Test
//	void testUser(){
//		log.info(new BCryptPasswordEncoder().encode("123456"));
//	}

//	@Test
//	void testObject(){
//		Tclass tclass=new Tclass();
//		if(tclass.user==null) log.info("tclass.user==null");
//	}
//	private class Tclass{
//		private User user;
//	}
//	@Test
//	void testTime(){
//		Calendar calendar =Calendar.getInstance();
//		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
//		long time0=calendar.getTime().getTime();
//		System.out.println("time0 ==> "+time0);
//	}
}
