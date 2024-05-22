package com.ra.login_register.dao;

import com.ra.login_register.model.Product;

import java.util.List;

public interface IProductDao {
	List<Product> findAll(int currentPage,int size);
	Product findById(Long id);
	void save(Product product);
	void deleteById(Long id);
	
	String getImageByProductId(Long id);
	Long countAllProduct();
}
