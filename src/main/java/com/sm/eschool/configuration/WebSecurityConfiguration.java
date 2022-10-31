package com.sm.eschool.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  {
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails appUser = User.withUsername("root")
			.password("root")
			.authorities("read")
			.build();
		
		return new InMemoryUserDetailsManager(appUser);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.httpBasic().disable()
			.authorizeHttpRequests(
				http -> http
					.mvcMatchers("/login", "/h2-console/**").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin().loginPage("/login")
			.and()
			.logout()
			.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers("/h2-console/**")
			.and()
			.headers().frameOptions().sameOrigin()
			.and()
			.build();
	}

}
