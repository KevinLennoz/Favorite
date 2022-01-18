package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.iservice.ArticleIService;
import fr.eql.al35.iservice.CartIService;
import fr.eql.al35.iservice.CustomIService;

@Controller
public class CartController {

	private final CartIService cartService;

	private final ArticleIService articleService;

	private final CustomIService customService;

	@Autowired
	private CartController(CartIService cartService,
			ArticleIService articleService,
			CustomIService customService) {
		this.cartService = cartService;
		this.articleService = articleService;
		this.customService = customService;
	}

	@PostMapping("/addToCart")
	public String displayAddToCart(@ModelAttribute("orderLine") OrderLineDTO orderLine, 
			Model model,
			HttpSession session) {

		if(cartService.enoughInStock(orderLine)){
			cartService.addOrderLineToCart((Cart) session.getAttribute("sessionCart"), orderLine);
			return "redirect:/products/all";
		}else {
			return "plusDeStock";
		}
	}
	
	@GetMapping("/cart")
	public String displayCart(Model model, HttpSession session) {
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		System.out.println(sessionCart);
		
		model.addAttribute("cart", sessionCart);
		model.addAttribute("orderLines", sessionCart.getOrderLines());
		model.addAttribute("total", cartService.getTotalCartPrice(sessionCart));
		
		return "cart";
	}
	
	@PostMapping("/addCustomOrderLineToCart")
	public String addCustomArticleToCart(@ModelAttribute("orderLine") OrderLineDTO orderLine,
			Model model,
			HttpSession session) {

		System.out.println(orderLine);
		
		articleService.updateCustomsInfos(orderLine);
		
		System.out.println(orderLine);
		
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		
		cartService.addOrderLineToCart(sessionCart, orderLine);

		return "redirect:/products/all";
	}
	
	/* @PostMapping("/cart")
	public String displayDeleteArticle(@RequestParam("index") Integer index, HttpSession session) {
		Cart sessionCart = (Cart) session.getAttribute("sessionCart");
		cartService.removeArticle(sessionCart, index);
		return "redirect:/cart";
	}*/
}
