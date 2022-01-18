package fr.eql.al35.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import fr.eql.al35.delegate.ProductDelegate;
import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.CustomDTO;
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
	
	@Override
	public void sessionCartGenerator(Model model, Cart sessionCart) {
		if(sessionCart == null) {
			model.addAttribute("sessionCart", new Cart());
		} else {
			model.addAttribute("sessionCart", sessionCart);
		}
	}
	
	@Override
	public boolean enoughInStock(OrderLineDTO orderLine) {
		List<StockDTO> stocks = productDelegate.getClothById(orderLine.getClothId()).getStocks();
		
		return stocks.stream().anyMatch(s -> s.getSize().equals(orderLine.getSize())
				&& s.getQuantity() >= orderLine.getQuantity());
	}

	@Override
	public void addOrderLineToCart(Cart cart, OrderLineDTO orderLine) {
		
		//Récupération des lignes deja existantes dans le cart
		Set<OrderLineDTO> orderLines = cart.getOrderLines();
		
		//Récupération du cloth à partir de son id
		ClothDTO cloth = productDelegate.getClothById(orderLine.getClothId());

		orderLine.setCloth(cloth);

		//Récupération du prix unitaire du cloth
		orderLine.setPrice(cloth.getPrice());
		
		//Récupération du prix total (quantité + customs)
		updateOrderLineTotalPrice(orderLine);
		
		orderLines.add(orderLine);
		
		cart.setClothQuantity(cart.getClothQuantity() + orderLine.getQuantity());
		cart.setTotalPrice(cart.getTotalPrice() + orderLine.getTotalPrice());
	}

	@Override
	public void updateOrderLineTotalPrice(OrderLineDTO orderLine) {
		Double total = 0.0;
		
		for (CustomDTO custom : orderLine.getCustoms()) {

			if (custom.getDesignId() != null) {
				total += custom.getDesign().getPrice() * orderLine.getQuantity();				
			}
		}
		
		total += orderLine.getPrice() * orderLine.getQuantity();

		orderLine.setTotalPrice(total);
	}
	
	private OrderLineDTO getOrderLineFromSet(Cart cart, int index) {
		List<OrderLineDTO> orderLines = new ArrayList<>(cart.getOrderLines());
		return orderLines.get(index);
	}

	@Override
	public void removeOrderLine(Cart cart, Integer index) {

		OrderLineDTO orderLine = getOrderLineFromSet(cart, index);
		cart.getOrderLines().remove(orderLine);
		cart.setClothQuantity(cart.getClothQuantity() - orderLine.getQuantity());
		cart.setTotalPrice(cart.getTotalPrice() - orderLine.getTotalPrice());
	}
}
