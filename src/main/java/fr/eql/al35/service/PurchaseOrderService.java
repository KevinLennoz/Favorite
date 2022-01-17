package fr.eql.al35.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.GlobalDelegate;
import fr.eql.al35.delegate.OrderDelegate;
import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.iservice.PurchaseOrderIService;
import fr.eql.al35.util.ListConvertor;

@Service
public class PurchaseOrderService implements PurchaseOrderIService {

	private final OrderDelegate orderDelegate;
	private final GlobalDelegate globalDelegate;

	@Autowired
	public PurchaseOrderService(OrderDelegate orderDelegate, GlobalDelegate globalDelegate) {
		this.orderDelegate = orderDelegate;
		this.globalDelegate = globalDelegate;
	}

	@Override
	public PurchaseOrderDTO createPurchaseOrder(Cart cart, PurchaseOrderDTO order) {
		order.setOrderLines(ListConvertor.convertToList(cart.getOrderLines()));
		return order;
	}

	@Override
	public void savePurchaseOrder(PurchaseOrderDTO order) {
		globalDelegate.saveOrder(order);
	}

	@Override
	public List<PurchaseOrderDTO> getAllByUserId(Integer userId) {
		return globalDelegate.getOrdersByUser(userId);
	}

	@Override
	public PurchaseOrderDTO getPurchaseOrderById(Integer orderId) {
		return globalDelegate.getOrderById(orderId);
	}

	@Override
	public List<PurchaseOrderDTO> getAllPurchaseOrders() {
		return orderDelegate.getAllOrders();
	}
}
