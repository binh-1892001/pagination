package com.ra.login_register.service;

import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.model.Users;

public interface IUserService {
	Users login(FormLogin formLogin);
	boolean register(FormRegister formRegister);
}
