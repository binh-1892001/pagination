package com.ra.login_register.controller;

import com.ra.login_register.dto.request.ProductRequest;
import com.ra.login_register.model.Product;
import com.ra.login_register.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping
	public String homeProduct(Model model,@RequestParam(defaultValue = "0") int currentPage,@RequestParam(defaultValue = "2") int size) {
		model.addAttribute("current",currentPage);
		model.addAttribute("totalPages",Math.ceil( (double) productService.countProduct() / size));
		model.addAttribute("products",productService.findAll(currentPage,size));
		return "products/listProduct";
	}
	
	@GetMapping("/initAddProduct")
	public String initAddProduct(Model model) {
		model.addAttribute("product",new ProductRequest());
		return "products/addProduct";
	}
	
	@PostMapping("/handleAddProduct")
	public String handleAddProduct(@ModelAttribute("product") ProductRequest productRequest) {
		productService.save(productRequest);
		return "redirect:/product";
	}
	// /product/{id}/edit
	@GetMapping("/{id}/edit")
	public String viewEditProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product",productService.findById(id));
		return "products/editProduct";
	}
	
	@PostMapping("/handleEditProduct/{id}")
	public String handleEditProduct(@ModelAttribute("product") ProductRequest productRequest,@PathVariable Long id) {
		productRequest.setId(id);
		productService.save(productRequest);
		return "redirect:/product";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteById(id);
		return "redirect:/product";
	}

}
