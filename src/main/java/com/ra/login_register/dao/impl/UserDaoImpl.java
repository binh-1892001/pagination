package com.ra.login_register.dao.impl;

import com.ra.login_register.dao.IUserDao;
import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements IUserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean register(Users users) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(users);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}
	
	@Override
	public Users login(String username,String password) {
		Session session = sessionFactory.openSession();
		try {
			Users users = session.createQuery("select u from Users u where u.username = :username and u.password = :password",Users.class)
					  .setParameter("username",username)
					  .setParameter("password",password)
					  .getSingleResult();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
