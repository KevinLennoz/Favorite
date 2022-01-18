package fr.eql.al35.iservice;

import org.springframework.ui.Model;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.OrderLineDTO;

public interface CartIService {
	
	void sessionCartGenerator(Model model, Cart sessionCart);

	void addOrderLineToCart(Cart cart, OrderLineDTO orderLine);

	boolean enoughInStock(OrderLineDTO orderLine);

	void updateOrderLineTotalPrice(OrderLineDTO orderLine);

	void removeOrderLine(Cart cart, Integer index);

}
