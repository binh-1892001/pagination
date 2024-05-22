package com.ra.login_register.service;

import com.ra.login_register.dto.request.ProductRequest;
import com.ra.login_register.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
	List<Product> findAll(int currentPage, int page);
	Product findById(Long id);
	void save(ProductRequest productRequest);
	void deleteById(Long id);
	Long countProduct();
}
