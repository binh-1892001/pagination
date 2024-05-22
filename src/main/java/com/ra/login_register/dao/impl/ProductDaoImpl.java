package com.ra.login_register.dao.impl;

import com.ra.login_register.dao.IProductDao;
import com.ra.login_register.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> findAll(int currentPage,int size) {
		Session session = sessionFactory.openSession();
		try {
			// HQL -> Hibernate Query Language
			return session.createQuery("from Product ", Product.class)
					  .setFirstResult(currentPage)
					  .setMaxResults(size)
					  .getResultList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public Product findById(Long id) {
		Session session = sessionFactory.openSession();
		try {
			// HQL -> Hibernate Query Language
			return session.get(Product.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void save(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			if (product.getId() == null) {
				// chức năng thêm mới
				session.save(product);
			} else {
				// chức năng update
				session.update(product);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(findById(id));
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public String getImageByProductId(Long id) {
		Session session = sessionFactory.openSession();
		try {
			return (String) session.createQuery("select p.image from Product p where p.id = :id")
					  .setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public Long countAllProduct() {
		Session session = sessionFactory.openSession();
		try {
			return (Long) session.createQuery("select count(p.id) from Product p").getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
}
