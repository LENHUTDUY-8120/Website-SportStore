package com.LND.SportStore.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private DataSource dataSource;
			
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.authorizeRequests().anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/validate")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/product")
			.failureUrl("/login?error=false")
			.and().exceptionHandling().accessDeniedPage("/login?denied=denied");
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				
				auth.jdbcAuthentication().dataSource(dataSource)
						.usersByUsernameQuery("SELECT USERNAME,PASSWORD,ENABLE FROM CUSTOMER WHERE USERNAME = ?")
						.authoritiesByUsernameQuery("SELECT USERNAME,ROLE FROM CUSTOMER WHERE USERNAME = ?")
						.passwordEncoder(encoder);						
		}
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/resources/**");
		}
		
}
