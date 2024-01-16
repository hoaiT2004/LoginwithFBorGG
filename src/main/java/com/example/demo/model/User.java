package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class User {

	@Id
	String name;
	String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
}
