package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepo;
import com.example.repository.ProductRepo;

@Service
public class ProductServiceHelper {

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Value("${file.upload-dir}")
    private String uploadDir;
	public String saveImage(MultipartFile image) throws IOException {
		Path directory=Paths.get(uploadDir);
		
		if(!Files.exists(directory)) {
			Files.createDirectories(directory);
		}
		
		String fileName=UUID.randomUUID()+"_"+image.getOriginalFilename();
		
		Path filePath=directory.resolve(fileName);
		
				
		Files.copy(image.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
		System.out.println(fileName);
		return fileName;
				
	}
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> getProductBySellerId(String sellerId){
		return productRepo.findBySellerId(sellerId);
	}
	
	public List<Category> categories(){
		return categoryRepo.findAll();
	}

	public List<Product> getProductByCategory(String categoryName) {
		
		return productRepo.findByCategoryIgnoreCase(categoryName);
	}
	
	public List<Product> search(String name,String descKeyword){
		return productRepo.findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(name, descKeyword);
	}
}
