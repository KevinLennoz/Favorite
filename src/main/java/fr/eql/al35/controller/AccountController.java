package fr.eql.al35.controller;

import javax.servlet.http.HttpSession;

import fr.eql.al35.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.iservice.CartIService;

@RestController
@CrossOrigin
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

	@GetMapping("/users/{userId}")
	public UserDTO displayUser(@PathVariable Integer userId) {
		return accountService.getUserById(userId);
	}

	@GetMapping("/switchAdmin")
    public String switchAdminAccount(Model model, HttpSession session) {
        model.addAttribute(SESSION_USER_PARAM, accountService.getAdminAccount());
        cartService.sessionCartGenerator(model, (Cart) session.getAttribute(SESSION_CART_PARAM));
        return "adminHome";
    }

	@PostMapping("/goodbye")
	public String close(SessionStatus status) {
		status.setComplete();
		return "home";
	}
}
