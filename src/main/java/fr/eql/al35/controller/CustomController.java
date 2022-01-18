package fr.eql.al35.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.dto.SizeDTO;
import fr.eql.al35.iservice.DesignIService;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class CustomController {
	
	private ProductIService productService;
	private DesignIService designService;
	
	private SizeDTO size;
	private Integer quantity;

	@Autowired
	private CustomController(ProductIService productService, DesignIService designService) {
		this.productService = productService;
		this.designService = designService;
	}

	@PostMapping("/generateCustom")
	public String displayGenrateCustom(@ModelAttribute("orderLine") OrderLineDTO orderLine, @RequestParam("clothId") Integer clothId,
			 Model model) {
		
		String category = productService.displayProductById(clothId).getProductType().getName();
		size = orderLine.getSize();
		quantity = orderLine.getQuantity();

		return "redirect:/custom/" + category + "/" + clothId;
	}
	
	@GetMapping("/custom/{category}/{clothId}")
	public String displayCustom(@PathVariable String category, @PathVariable Integer clothId, Model model) {
		
		OrderLineDTO orderLine = new OrderLineDTO();
		orderLine.setSize(size);
		orderLine.setQuantity(quantity);
		model.addAttribute("product", productService.displayProductById(clothId));
		model.addAttribute("categories", productService.displayAllCategories());
		model.addAttribute("designs", designService.displayAllDesign());
		model.addAttribute("orderLine", orderLine);
		
		return "custom";
	}
}
