package com.ra.login_register.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
	private Long id;
	private String name;
	private MultipartFile file;
	private Boolean status;
}
