package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/login")
	public String login() {
		return "login";
	}

	@PostMapping("/api/login")
	public String login(@ModelAttribute User user, HttpSession session) {
		Optional<User> optional = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
		if (!optional.isPresent())
			return "redirect:/api/login";
		session.setAttribute("user", user);
		return "index";
	}

	@GetMapping("/api/create")
	public String create(){
		return "create";
	}

	@PostMapping("/api/create")
	public String create(@ModelAttribute User user) {
		Optional<User> optional = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
		if (!optional.isPresent()) {
			userRepository.save(user);
			return "login";
		}
		return "redirect:/api/create";
	}

	@PostMapping("/index")
	public String displayDashboard(Model model){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
			DefaultOAuth2User user = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
			model.addAttribute("userDetails", user.getAttribute("name")!= null ?user.getAttribute("name"):user.getAttribute("login"));
		}else {
			User user = (User) securityContext.getAuthentication().getPrincipal();
			Optional<User> optional = userRepository.findByName(user.getName());
			User users = optional.get();
			model.addAttribute("userDetails", users.getName());
		}
		return "index";
	}
}

