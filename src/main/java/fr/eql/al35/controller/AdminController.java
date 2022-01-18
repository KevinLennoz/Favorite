package fr.eql.al35.controller;

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

	@GetMapping("/admin/products")
	public String displayAdminProduct( Model model) {
		model.addAttribute("clothes", productService.displayAllProducts());
		return "adminProducts";
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

	/*
	@PostMapping("/upDateProducts")
	public String upDateProducts(@ModelAttribute("product")Product product, @RequestParam("idProduct") Integer idProduct, Model model) {
		model.addAttribute("productTypes", productService.displayAllCategories());
		model.addAttribute("product", productService.upDate(idProduct, product));

		return "redirect:/admin/products/"+idProduct;
	}

	@PostMapping("/upDateStock")
	public String upDateStock(@ModelAttribute("stock")Stock stock, @RequestParam("idStock") Integer idStock, @RequestParam("idProduct") Integer idProduct,
			@RequestParam String sizeLabel, Model model) {
		stock.setProduct(productService.displayProductById(idProduct));
		Size size = new Size();
		size.setLabel(sizeLabel);
		stock.setSize(size);
		stockService.upDate(idStock, stock);
		Product product = productService.displayProductById(idProduct);
		Integer quantity = 0;
		for (Stock s : product.getStocks()) {
			quantity+=s.getQuantity();
		}
		product.setQuantity(quantity);
		model.addAttribute("productTypes", productService.displayAllCategories());
		model.addAttribute("product", productService.upDate(idProduct, product));

		return "redirect:/admin/products/"+idProduct;
	}

	@PostMapping("/upDatePhotos")
	public String upDatePhoto(@ModelAttribute("photo")Photo photo,
							  @RequestParam("idPhoto") Integer idPhoto,
							  @RequestParam("pathPhoto") String pathPhoto,
							  @RequestParam("descriptionPhoto") String descriptionPhoto,
							  @RequestParam("idProduct") Integer idProduct,
							  @RequestParam("index") Integer index,
							  Model model) {
		model.addAttribute("productTypes", productService.displayAllCategories());
		photoService.upDatePhoto(idPhoto, pathPhoto, descriptionPhoto, idProduct, index);
		model.addAttribute("product", productService.displayProductById(idProduct));
		return "adminProductInfo";
	}

	@GetMapping("/admin/command")
	public String displayAdminCommand( Model model) {
		model.addAttribute("commands", commandService.displayAllCommands());
		model.addAttribute("statusRef", adminService.displayAllStatus());
		model.addAttribute("vatRef", adminService.displayAllVats());
		model.addAttribute("payModeRef", adminService.displayAllPayModes());

		return "adminCommand";
	}

	@PostMapping("/upDateCommands")
	public String upDateCommands(@ModelAttribute("command")Command command, Model model) {
		commandService.updateCommand(command);
		model.addAttribute("command", commandService.updateCommand(command));
		model.addAttribute("commands", commandService.displayAllCommands());
		model.addAttribute("statusRef", adminService.displayAllStatus());
		model.addAttribute("vatRef", adminService.displayAllVats());
		model.addAttribute("payModeRef", adminService.displayAllPayModes());
		return "adminCommand";
	}

	@GetMapping("/admin/products/{id}")
	public String displayProduct(@PathVariable Integer id, Model model) {
		Stock stock = new Stock();
		model.addAttribute("stock", stock);
		model.addAttribute("product", productService.displayProductById(id));
		model.addAttribute("productTypes", productService.displayAllCategories());
		model.addAttribute("index", 0);
		return "adminProductInfo";
	}
	@GetMapping("/admin/products/delete/{id}")
	public String deleteProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("products", productService.displayAllProducts());
		productService.setDeleteProduct(id);
		return "adminProducts";
	}

	@GetMapping("/admin/product/add")
	public String adminAddProduct( Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		model.addAttribute("productTypes", productService.displayAllCategories());
		return "adminAddProduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product")Product product, Model model) {
		productService.addProduct(product);
		model.addAttribute("products", productService.displayAllProducts());
		return "adminProducts";
	}

	 */
}
