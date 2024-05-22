package com.ra.login_register.controller;

import com.ra.login_register.constants.RoleName;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.model.Users;
import com.ra.login_register.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("formLogin", new FormLogin());
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("formRegister", new FormRegister());
		return "register";
	}
	
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute("formLogin") FormLogin formLogin, HttpSession session) {
		Users users = userService.login(formLogin);
		if (users != null) {
			session.setAttribute("user", users);
		} else {
			return "redirect:/";
		}
		if (users.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
			return "redirect:/admin";
		}
		return "home";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/403")
	public String forbidden() {
		return "_403";
	}
	
	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("formRegister") FormRegister formRegister) {
		userService.register(formRegister);
		return "redirect:/";
	}
	
}
