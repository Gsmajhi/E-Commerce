package com.gsmajhi.controller;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsmajhi.dao.Category;
import com.gsmajhi.dao.Product;
import com.gsmajhi.service.PService;

@Controller
public class ProductController {
	
	@Autowired
	PService pService;
	
	@GetMapping("/productform")
	public String showProductForm(Model m) {
		
	List<Category>	categories=pService.getCategories();
		m.addAttribute("product",new Product());
		m.addAttribute("category",categories);
		
		return "productform";
	}
	
	
	@PostMapping("/addproduct")
	public String addProduct(@RequestParam("productName") String productName,
			@RequestParam("category") String category,
			@RequestParam("description") String description,
			@RequestParam("price") String price,
			@RequestParam("image") MultipartFile file, RedirectAttributes redirectAtt) {
		System.out.println("addproduct method");
		Product product=new Product();
		product.setProductName(productName);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(price);
		Product   pro =pService.addProduct(product);
		if(pro!=null) {
			String proId=pro.getProductId();
			if(pService.addProductImage(file, proId)) {
				redirectAtt.addFlashAttribute("msg","Product Added");
			}
			
			redirectAtt.addFlashAttribute("msg","Product Added But Image not Added");
		}
		else {
			redirectAtt.addFlashAttribute("msg","Something went wrong");
		}
		return "redirect:/productform";
		
	}

	@GetMapping("/myproducts")
	public String getProducts(Model m){
		List<Product> product=pService.findProductBySellerId();
		
		m.addAttribute("product",product);
		return "myproducts";
	}
	
	@GetMapping("/productedit/{productId}")
	public String productEditForm(@PathVariable String productId,Model model) {
		Product product=pService.getProductById(productId);
		model.addAttribute("product",product);
		return "editproduct";
	}
	
	
	@PostMapping("/updateproduct/{productId}")
	public String updateProduct(@ModelAttribute Product product,@PathVariable String productId,RedirectAttributes redAtts) {
		String msg= pService.updateProduct(productId, product);
		redAtts.addFlashAttribute("msg",msg);
		
		return "redirect:/myproducts";
	}
	
	@GetMapping("/category/{categoryName}")
	public String getProductByCategory(@PathVariable String categoryName,Model m) {
		List<Product> product=pService.getProductByCategory(categoryName).
				stream().map(prod->{Product pro=new Product();
				pro.setProductName(prod.getProductName());
				pro.setCategory(prod.getCategory());
				pro.setDescription(prod.getDescription());
				pro.setImagePath("http://localhost:8080/images/"+prod.getImagePath());
				pro.setPrice(prod.getPrice());
				pro.setQuantity(prod.getQuantity());
				pro.setProductId(prod.getProductId());
				return pro;
				}).toList();
		m.addAttribute("list",product);
		System.out.println(product);
		return "index";
	}
}
