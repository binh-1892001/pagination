package com.ra.login_register.dao.impl;

import com.ra.login_register.constants.RoleName;
import com.ra.login_register.dao.IRoleDao;
import com.ra.login_register.model.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements IRoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Roles findByRoleName(RoleName roleName) {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("select r from Roles r where r.roleName = :roleName", Roles.class)
					  .setParameter("roleName",roleName)
					  .getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
