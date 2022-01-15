package fr.eql.al35.iservice;

import fr.eql.al35.entity.Cart;

import org.springframework.ui.Model;

public interface CartIService {
	
	void sessionCartGenerator(Model model, Cart sessionCart);

}
