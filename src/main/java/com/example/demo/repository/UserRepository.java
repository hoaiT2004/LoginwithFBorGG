package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByNameAndPassword(String username,String password);
	Optional<User> findByName(String username);
}
