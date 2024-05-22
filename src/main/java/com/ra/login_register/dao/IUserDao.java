package com.ra.login_register.dao;

import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.model.Users;

public interface IUserDao {
	boolean register(Users users);
	Users login(String username,String password);
}
