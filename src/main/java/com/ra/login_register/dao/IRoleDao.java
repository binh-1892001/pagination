package com.ra.login_register.dao;

import com.ra.login_register.constants.RoleName;
import com.ra.login_register.model.Roles;

public interface IRoleDao {
	Roles findByRoleName(RoleName roleName);
}
