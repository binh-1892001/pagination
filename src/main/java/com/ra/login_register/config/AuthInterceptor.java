package com.ra.login_register.config;

import com.ra.login_register.constants.RoleName;
import com.ra.login_register.model.Users;
import org.hibernate.Session;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Users users = (Users) request.getSession().getAttribute("user");
		if(users != null) {
			if(users.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
				return true;
			} else {
				response.sendRedirect("/403");
				return false;
			}
		} else {
			response.sendRedirect("/");
			return false;
		}
	}
}
