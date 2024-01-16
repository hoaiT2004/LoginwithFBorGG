package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomerOAuth2UserService auth2UserService;

//	@Autowired
//	private UserService userDetailsService;

//	@Autowired
//	private CustomSuccessHandler customSuccessHandler;
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//		auth.setUserDetailsService(userDetailsService);
//		auth.setPasswordEncoder(passwordEncoder());
//		return auth;
//	}
//
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http	
	        		
	        		.authorizeRequests()
					.requestMatchers("/api/login","/api/create")
					.permitAll()
	                .anyRequest().authenticated()
	                .and()
					.oauth2Login()
	                	.loginPage("/api/login")
	                	.userInfoEndpoint()
	                	.userService(auth2UserService)
	                .and()
	                .and()
	                .logout() // lật chức năng đăng xuất và cấu hình các tùy chọn liên quan đến đăng xuất.
	                .invalidateHttpSession(true) // Đánh dấu phiên người dùng (HttpSession) là không hợp lệ sau khi đăng xuất.
	                .clearAuthentication(true) // Xóa thông tin xác thực của người dùng sau khi đăng xuất.
	                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")) // Xác định URL để xử lý yêu cầu đăng
	                // xuất.(tên trùng với link đăng xuất bên html việc còn lại thì Java sẽ xử lí)
	                .logoutSuccessUrl("/api/login?logout") // Xác định URL mà người dùng sẽ được chuyển đến sau khi đăng
	                // xuất thành công.
	                .permitAll();
	        
	        return http.build();
	    }
}
