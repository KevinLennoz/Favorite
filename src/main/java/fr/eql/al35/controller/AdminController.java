package fr.eql.al35.controller;

import fr.eql.al35.dto.ClothDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.iservice.AdminIService;
import fr.eql.al35.iservice.PurchaseOrderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.iservice.ProductIService;

@Controller
public class AdminController {

	private final ProductIService productService;
	private final AccountIService accountService;
	private final AdminIService adminService;
	private final PurchaseOrderIService purchaseOrderService;

	private static final String ADMIN_USERS_PAGE = "redirect:/admin/users";
	private static final String MODEL_CLOTHES = "clothes";
	private static final String ADMIN_PRODUCTS = "adminProducts";

	@Autowired
	public AdminController(ProductIService productService, AccountIService accountService, AdminIService adminService, PurchaseOrderIService purchaseOrderService) {
		this.productService = productService;
		this.accountService = accountService;
		this.adminService = adminService;
		this.purchaseOrderService = purchaseOrderService;
	}

	@GetMapping("/admin/home")
	public String redirectAdminHome( Model model) {
		return "adminHome";
	}

	@GetMapping("/admin/users")
	public String displayUsers(Model model) {
		model.addAttribute("users", adminService.displayAllUsers());
		return "adminUsers";
	}

	@GetMapping("/admin/users/{userId}")
	public String displayUser(@PathVariable Integer userId, Model model) {
		model.addAttribute("user", adminService.displayUser(userId));
		model.addAttribute("genders", accountService.getAllGenders());
		model.addAttribute("userTypes", accountService.getAllUserTypes());
		return "adminUserInfo";
	}

	@PostMapping("/admin/user/{userId}/unsubscribe")
	public String unsubscribeUser(@PathVariable Integer userId) {
		adminService.unsubscribeUser(userId);
		return ADMIN_USERS_PAGE;
	}

	@PostMapping("/admin/user/{userId}/subscribe")
	public String subscribeUser(@PathVariable Integer userId) {
		adminService.subscribeUser(userId);
		return ADMIN_USERS_PAGE;
	}

	@PostMapping("/updateUser/{userId}")
	public String updateUser(@ModelAttribute UserDTO user, @PathVariable Integer userId) {
		adminService.updateUser(userId, user);
		return ADMIN_USERS_PAGE;
	}

	@GetMapping("/admin/orders/{orderId}")
	public String displayCommand(@PathVariable Integer orderId, Model model) {
		model.addAttribute("order", purchaseOrderService.getPurchaseOrderById(orderId));
		return "adminOrderInfo";
	}

	@GetMapping("/admin/products")
	public String displayAdminProduct( Model model) {
		model.addAttribute(MODEL_CLOTHES, productService.displayAllProducts());
		return ADMIN_PRODUCTS;
	}

	@GetMapping("/admin/products/{productId}")
	public String displayProductDetail(@PathVariable Integer productId, Model model) {
		model.addAttribute("newQuantity", 0);
		model.addAttribute("product", productService.displayProductById(productId));
		model.addAttribute("productTypes", productService.displayAllCategories());
		return "adminProductInfo";
	}

	@PostMapping("/admin/products/{productId}/update")
	public String upDateProducts(@ModelAttribute ClothDTO product, @PathVariable Integer productId) {
		productService.updateProduct(productId, product);
		return "redirect:/admin/products/"+productId;
	}

	@GetMapping("/admin/products/{productId}/delete")
	public String deleteProduct(@PathVariable Integer productId, Model model) {
		productService.deleteProduct(productId);
		model.addAttribute(MODEL_CLOTHES, productService.displayAllProducts());
		return ADMIN_PRODUCTS;
	}

	@PostMapping("/updateStock")
	public String upDateStock(@RequestParam("newQuantity")Integer newQuantity, @RequestParam("stockId") Integer stockId,
							  @RequestParam("productId") Integer productId) {
		productService.updateStock(stockId, newQuantity, productId);
		return "redirect:/admin/products/"+productId;
	}

	@GetMapping("/admin/products/add")
	public String adminAddProduct( Model model) {
		model.addAttribute("product", ClothDTO.initStockClothDTO(new ClothDTO()));
		model.addAttribute("productTypes", productService.displayAllCategories());
		return "adminAddProduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product") ClothDTO product, Model model) {
		productService.addProduct(product);
		model.addAttribute(MODEL_CLOTHES, productService.displayAllProducts());
		return ADMIN_PRODUCTS;
	}

}
