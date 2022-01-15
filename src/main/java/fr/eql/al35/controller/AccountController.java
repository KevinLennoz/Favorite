package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.iservice.CartIService;

@Controller
@SessionAttributes({"sessionCart", "sessionUser"})
public class AccountController {
	
	private final AccountIService accountService;
	private final CartIService cartService;
	private static final String SESSION_USER_PARAM = "sessionUser";
	private static final String SESSION_CART_PARAM = "sessionCart";
	
	@Autowired
	public AccountController(AccountIService accountService, CartIService cartService) {
		this.accountService = accountService;
		this.cartService = cartService;
	}

	@GetMapping({"/", "/home"})
	public String displayHome(Model model) {
		//Utilisateur 3 en dur en session (pour ne pas avoir à créer de compte)
		model.addAttribute(SESSION_USER_PARAM, accountService.getUser3());
		cartService.sessionCartGenerator(model, null);
		return "home";
	}
	
	@GetMapping("/switchAdmin")
    public String switchAdminAccount(Model model, HttpSession session) {
        model.addAttribute(SESSION_USER_PARAM, accountService.getAdminAccount());
        cartService.sessionCartGenerator(model, (Cart) session.getAttribute(SESSION_CART_PARAM));
        return "adminHome";
    }
	
	@GetMapping("/switchUser")
	public String switchUser3Account(Model model, HttpSession session) {
		model.addAttribute(SESSION_USER_PARAM, accountService.getUser3());
		cartService.sessionCartGenerator(model, (Cart) session.getAttribute(SESSION_CART_PARAM));
		return "home";
	}
	
	@PostMapping("/goodbye")
	public String close(SessionStatus status) {
		status.setComplete();
		return "home";
	}
}
