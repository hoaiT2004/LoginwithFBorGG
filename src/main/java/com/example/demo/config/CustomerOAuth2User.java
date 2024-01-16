package com.example.demo.config;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomerOAuth2User implements OAuth2User{


	private OAuth2User auth2User;

	public CustomerOAuth2User(OAuth2User auth2User) {
		this.auth2User = auth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return auth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return auth2User.getAuthorities();
	}

	@Override
	public String getName() {
		//System.out.println("Name:"+auth2User.getAttribute("name"));
		// TODO Auto-generated method stub

		return auth2User.getAttribute("name");
	}

	

}
