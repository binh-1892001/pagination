package com.ra.login_register.service.impl;

import com.ra.login_register.dao.IProductDao;
import com.ra.login_register.dto.request.ProductRequest;
import com.ra.login_register.model.Product;
import com.ra.login_register.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;
	@Autowired
	private FileService fileService;
	
	@Override
	public List<Product> findAll(int currentPage, int size) {
		return productDao.findAll(currentPage,size);
	}
	
	@Override
	public Product findById(Long id) {
		return productDao.findById(id);
	}
	
	@Override
	public void save(ProductRequest productRequest) {
		Product product = Product.builder()
				  .id(productRequest.getId())
				  .name(productRequest.getName())
				  .status(productRequest.getStatus())
				  .build();
		if(productRequest.getId() == null) {
			product.setImage(fileService.uploadFileToServer(productRequest.getFile()));
		} else {
			if(productRequest.getFile() != null && productRequest.getFile().getSize() > 0) {
				product.setImage(fileService.uploadFileToServer(productRequest.getFile()));
			} else {
				product.setImage(productDao.getImageByProductId(productRequest.getId()));
			}
		}
		productDao.save(product);
	}
	
	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}
	
	@Override
	public Long countProduct() {
		return productDao.countAllProduct();
	}
}
