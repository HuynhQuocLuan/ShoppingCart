package com.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shoppingcart.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("userDetailsServiceImpl")
//	private UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("myDBAuthenticationService")
	private MyDBAuthenticationService myDBAuthenticationService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myDBAuthenticationService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());

		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/orderList", "/order","/accountInfo").access("hasAnyRole('EMPLOYEE', 'MANAGER')");
//		http.authorizeRequests().antMatchers("/productList","/order").access("hasAnyRole('CUSTOMER')");
		http.authorizeRequests().antMatchers("/product").access("hasRole('MANAGER')");

		// denied query
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/accountInfo")
			.failureUrl("/login?error=true")
			.usernameParameter("userName")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication().withUser("employee").password("employee").authorities("ROLE_EMPLOYEE"); authentication in memory
//		auth.inMemoryAuthentication().withUser("manager").password("manager").authorities("ROLE_MANAGER");
//		auth.authenticationProvider(authProvider());
//		auth.userDetailsService(userDetailsService);
//		auth.userDetailsService(myDBAuthenticationService); // authenitcation in dataBase
		auth.authenticationProvider(authProvider());

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	}

}
