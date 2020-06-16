package com.wdy.yunplm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.security.authentication.MyAuthenctiationFailureHandler;
import com.wdy.yunplm.security.authentication.MyAuthenticationSuccessHandler;
import com.wdy.yunplm.security.authentication.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity //开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private UserDetailsService userDetailsService;
	@Resource
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Resource
	private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
	@Resource
	private MyLogoutSuccessHandler myLogoutSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/login", "/api/v1/login_p", "/api/v1/hello", "/swagger-ui.html");
	}

	//定义哪些url被拦截or放行
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.authorizeRequests()//开启登录配置
				.antMatchers("/auth/**",
						"/hello", "/swagger-ui.html").permitAll()
//				.antMatchers("/api/v1/test").hasRole("SUPERADMIN")
//                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
				.and()

//				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//					@Override
//					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//						o.setSecurityMetadataSource(metadataSource);
//						o.setAccessDecisionManager(urlAccessDecisionManager);
//						return o;
//					}
//				})
//				.and()

				.formLogin()
//				.loginPage("/login")//定义登录页面，未登录时，访问普通接口，会自动跳转到该页面
				.loginProcessingUrl("/auth/login")//登录处理接口
//				.usernameParameter("uname")//定义登录时，用户名的 key，默认为 username
//				.passwordParameter("pwd")//定义登录时，用户密码的 key，默认为 password
				.successHandler(myAuthenticationSuccessHandler)
				.failureHandler(myAuthenctiationFailureHandler)
				.permitAll()//和表单登录相关的接口统统都直接通过
				.and()

				.logout()
				.logoutUrl("/auth/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessHandler(myLogoutSuccessHandler)
				.permitAll()
				.and()

//				.httpBasic()
//				.and()

				.exceptionHandling()
				//取消重定向
				.authenticationEntryPoint(new AuthenticationEntryPoint() {
					@Override
					public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
						resp.setContentType("application/json;charset=utf-8");
						PrintWriter out = resp.getWriter();
						String msg = "访问失败！";
						if (authException instanceof InsufficientAuthenticationException) {
							msg = "请求失败，你还没有权限!";
						}
						out.write(new ObjectMapper().writeValueAsString(Result.failure(msg)));
						out.flush();
						out.close();
					}
				})
				.and()

				.csrf()
				.disable();
	}
}
