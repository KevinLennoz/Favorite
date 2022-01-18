package fr.eql.al35.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.dto.StockDTO;
import fr.eql.al35.iservice.CartIService;

@Service
public class CartService implements CartIService {

	private final ProductDelegate productDelegate;

	@Autowired
	private CartService(ProductDelegate productDelegate) {
		this.productDelegate = productDelegate;
	}


	/*@Override
	public int getCartProductsQuantity(Cart cart) {

		Set<Article> articles = cart.getArticles();
		int articlesQuantity = 0;

		for (Article article : articles) {
			articlesQuantity += article.getQuantity();
		}

		return articlesQuantity;
	}*/

	@Override
	public void addOrderLineToCart(Cart cart, OrderLineDTO orderLine) {
		Set<OrderLineDTO> orderLines = new HashSet<>();
		orderLines.add(orderLine);
	}
	/*
	@Override
	public double getTotalPriceCart(Cart cart) {
		Set<Article> articles = cart.getArticles();
		double total = 0.0;
		double sousTotal = 0.0;
		for (Article article : articles) {
			sousTotal = article.getProduct().getPrice() * article.getQuantity();
			total = total + sousTotal;
		}		
		return total;
	}

	@Override
	public Article getArticle(Cart cart, int index) {
		ArrayList<Article> articles = new ArrayList<>(cart.getArticles());
		return articles.get(index);
	}

	@Override
	public void removeArticle(Cart cart, int index) {
		Article article = this.getArticle(cart, index);
		cart.getArticles().remove(article);
		cart.setArticlesQuantity(cart.getArticlesQuantity()-article.getQuantity());
		cart.setPrice(cart.getPrice()-article.getPrice()*article.getQuantity());
	}*/

	@Override
	public void sessionCartGenerator(Model model, Cart sessionCart) {
		if(sessionCart == null) {
			model.addAttribute("sessionCart", new Cart());
		} else {
			model.addAttribute("sessionCart", sessionCart);
		}
	}
	
	
	@Override
	public Double getTotalCartPrice(Cart cart) {
		
		Double total = 0.0;
		
		for (OrderLineDTO line : cart.getOrderLines()) {
			total = total + (line.getQuantity() * line.getPrice());
		}
		
		return total;
	}


	@Override
	public boolean enoughInStock(OrderLineDTO orderLine) {
		
		List<StockDTO> stocks = productDelegate.getClothById(orderLine.getClothId()).getStocks();
		
		return stocks.stream().anyMatch(s -> {
			if(s.getSize().equals(orderLine.getSize()) 
					&& s.getQuantity() >= orderLine.getQuantity()) {
				return true;
			}
			return false;
		});
	}
}
