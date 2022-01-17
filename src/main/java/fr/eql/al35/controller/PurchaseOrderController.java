package fr.eql.al35.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.iservice.PurchaseOrderIService;

@Controller
public class PurchaseOrderController {

	private final PurchaseOrderIService purchaseOrderService;
	List<PurchaseOrderDTO> purchaseOrders = new ArrayList<>();
	private static final String SESSION_USER_PARAM = "sessionUser";

	@Autowired
	public PurchaseOrderController(PurchaseOrderIService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}

	@GetMapping("/myOrders")
	public String userPurchaseOrders(Model model, HttpSession session) {
		UserDTO sessionUser = (UserDTO) session.getAttribute(SESSION_USER_PARAM);
		purchaseOrders = purchaseOrderService.getAllByUserId(sessionUser.getId());
		purchaseOrders.sort((o2,o1) -> o1.getCreationDate().compareTo(o2.getCreationDate()));
		model.addAttribute("orders", purchaseOrders);
		return "myOrders";
	}

	@GetMapping("/orders/{id}")
	public String displayOrder(@PathVariable Integer id, Model model, HttpSession session) {
		UserDTO sessionUser = (UserDTO) session.getAttribute(SESSION_USER_PARAM);
		PurchaseOrderDTO order = purchaseOrderService.getPurchaseOrderById(id);

		if (order.getUserId().equals(sessionUser.getId())) {
			model.addAttribute("order", order);
			return "/order";
		} 	else {
			return "/unauthorized";
		}
	}
}
