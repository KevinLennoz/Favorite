package fr.eql.al35.controller;

import fr.eql.al35.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.iservice.ProductIService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {

	private final ProductIService productService;

	@Autowired
	public ProductController(ProductIService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ProductsDisplayDTO displayAllProducts() {
		ProductsDisplayDTO modelDTO = new ProductsDisplayDTO();
		modelDTO.setProducts(productService.displayAvailableProducts());
		modelDTO.setCategories(productService.displayAllCategories());
		return modelDTO;
	}
	
	@GetMapping("/products/types/{type}")
	public ProductsDisplayDTO displayAllProductsByType(@PathVariable String type) {
		ProductsDisplayDTO modelDTO = new ProductsDisplayDTO();
		modelDTO.setProducts(productService.displayByProductTypeIgnoringCase(type));
		return modelDTO;
	}

	@GetMapping("/products/{id}")
	public ProductDisplayDTO displayProduct (@PathVariable Integer id) {
		ProductDisplayDTO modelDTO = new ProductDisplayDTO();
		modelDTO.setProduct(productService.displayProductById(id));
		modelDTO.setDesigns(productService.displayAllDesign());
		return modelDTO;
	}

}
