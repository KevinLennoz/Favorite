package fr.eql.al35.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.OrderLineDTO;
import fr.eql.al35.dto.OrderLineForProductWSDTO;
import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.service.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    private static final String SESSION_USER_PARAM = "sessionUser";

    private static final String SESSION_CART_PARAM = "sessionCart";

    private static final String SESSION_PURCHASE_ORDER_PARAM = "sessionPurchaseOrder";

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    public PaymentController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/payment")
    public String displayPayment(Model model, HttpSession session) {
        Cart sessionCart = (Cart) session.getAttribute(SESSION_CART_PARAM);
        Double amountToPay = sessionCart.getOrderLines().stream().mapToDouble(OrderLineDTO::getPrice).sum();

        PurchaseOrderDTO purchaseOrder = purchaseOrderService.createPurchaseOrder(sessionCart, new PurchaseOrderDTO());
        purchaseOrder.setTaxInPrice(amountToPay);
        purchaseOrder.setTaxOutPrice(amountToPay);

        UserDTO sessionUser = (UserDTO) session.getAttribute(SESSION_USER_PARAM);

        model.addAttribute(SESSION_PURCHASE_ORDER_PARAM, purchaseOrder);
        model.addAttribute(SESSION_USER_PARAM, sessionUser);
        return "payment";
    }

    @PostMapping("/newCommand")
    public String createPurchaseOrder(HttpSession session,
                                      @ModelAttribute(SESSION_PURCHASE_ORDER_PARAM) PurchaseOrderDTO purchaseOrder) {
        UserDTO sessionUser = (UserDTO) session.getAttribute(SESSION_USER_PARAM);

        List<OrderLineForProductWSDTO> orderLines = new ArrayList<>();

        purchaseOrder.setUserId(sessionUser.getId());
        purchaseOrder.setReference(writeReference(sessionUser));
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setTaxInPrice(
                purchaseOrder.getOrderLines().stream().mapToDouble(OrderLineDTO::getPrice).sum());
        purchaseOrder.setTaxOutPrice(purchaseOrder.getTaxInPrice());
        purchaseOrder.setUuid(UUID.randomUUID().toString());

        purchaseOrder.getOrderLines().forEach(orderLine -> orderLines.add(
                    new OrderLineForProductWSDTO(orderLine.getCloth(), orderLine.getSize(), orderLine.getQuantity())));

        purchaseOrderService.updateStocks(orderLines);

        purchaseOrderService.savePurchaseOrder(purchaseOrder); //stocker en BDD command et addresses

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return "redirect:home";
    }

    private String writeReference(UserDTO user) {
        return "CMD_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss")) + "_Client_" +
               user.getId();
    }

}
