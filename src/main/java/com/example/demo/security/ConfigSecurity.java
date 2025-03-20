package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfigSecurity {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	InMemoryUserDetailsManager userDetailsService() {
//		UserDetails admin = User
//				.withUsername("001")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();
//		UserDetails student = User
//				.withUsername("002")
//				.password(passwordEncoder().encode("user"))
//				.roles("USER")
//				.build();
//		System.out.printf("admin -> [%s]\n", passwordEncoder().encode("admin"));
//		System.out.printf("user -> [%s]\n", passwordEncoder().encode("user"));
//		return new InMemoryUserDetailsManager(admin, student);
//	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/dashboard")
				);
//		http.logout(logout -> logout.logoutSuccessUrl("/login"));
		http.authorizeHttpRequests(authz -> authz
//				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//				.requestMatchers("/login").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/img/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/userRegist/regist").permitAll()
				.requestMatchers("/userRegist/registExcecute").permitAll()
				.anyRequest().authenticated()
				);
		return http.build();
	}

}
