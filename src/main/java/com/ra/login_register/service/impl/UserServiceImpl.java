package com.ra.login_register.service.impl;

import com.ra.login_register.constants.RoleName;
import com.ra.login_register.dao.IRoleDao;
import com.ra.login_register.dao.IUserDao;
import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.model.Roles;
import com.ra.login_register.model.Users;
import com.ra.login_register.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public Users login(FormLogin formLogin) {
		return userDao.login(formLogin.getUsername(), formLogin.getPassword());
	}
	
	@Override
	public boolean register(FormRegister formRegister) {
		Set<Roles> roles = new HashSet<>();
		roles.add(roleDao.findByRoleName(RoleName.ROLE_USER));
		Users users = Users.builder()
				  .fullName(formRegister.getFullName())
				  .username(formRegister.getUsername())
				  .password(formRegister.getPassword())
				  .roles(roles)
				  .status(true)
				  .build();
		return userDao.register(users);
	}
}
