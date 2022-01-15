package fr.eql.al35.iservice;

import org.springframework.ui.Model;

import fr.eql.al35.dto.Cart;

public interface CartIService {
	
	void sessionCartGenerator(Model model, Cart sessionCart);

}
