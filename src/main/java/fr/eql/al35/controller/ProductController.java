package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.dto.ProductTypeDTO;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class ProductController {

	private ProductIService productService;
	private static final String CATEGORIES_PARAM = "categories";
	private static final String PRODUCTS_PARAM = "products";
	private static final String PRODUCT_PARAM = "product";
	private static final String PRODUCT_TYPE_PARAM = "productType";
	private static final String ORDER_LINE_PARAM = "orderLine";

	@Autowired
	public ProductController(ProductIService productService) {
		this.productService = productService;
	}

	@GetMapping("/products/all")
	public String displayAllProducts(Model model) {
		model.addAttribute(PRODUCTS_PARAM, productService.displayAvailableProducts());
		model.addAttribute(CATEGORIES_PARAM, productService.displayAllCategories());
		model.addAttribute(PRODUCT_TYPE_PARAM, new ProductTypeDTO());
		return "showcase";
	}
	
	@GetMapping("/products/{category}/{id}")
	public String displayProduct(@PathVariable String category, @PathVariable Integer id, Model model) {
		model.addAttribute(PRODUCT_PARAM, productService.displayProductById(id));
		model.addAttribute(CATEGORIES_PARAM, productService.displayAllCategories());
		model.addAttribute(ORDER_LINE_PARAM, new OrderLineDTO());
		return "productSheet";
	}
	@GetMapping("/products/{productType}")
	public String displayProductsByType(@PathVariable ProductTypeDTO productType, Model model) {
		model.addAttribute(CATEGORIES_PARAM, productService.displayAllCategories());
		model.addAttribute(PRODUCTS_PARAM, productService.displayByProductType(productType));
		model.addAttribute(PRODUCT_TYPE_PARAM, productType);
		return "showcase";
	}
}
