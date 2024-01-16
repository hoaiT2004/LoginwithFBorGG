package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.Setter;

import java.util.Optional;

@Service
public class CustomerOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Override 
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		CustomerOAuth2User customerOAuth2User = new CustomerOAuth2User(super.loadUser(userRequest));
		Optional<User> optional = userRepository.findByName(customerOAuth2User.getName()+"@gmail.com");
		if(!optional.isPresent()){
			User user = new User();
			user.setName(customerOAuth2User.getName()+"@gmail.com");
			user.setPassword("0343199493");
			userRepository.save(user);
		}
		return new CustomerOAuth2User(super.loadUser(userRequest));
	}
}
