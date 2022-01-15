package fr.eql.al35.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.GlobalDelegate;
import fr.eql.al35.delegate.OrderDelegate;
import fr.eql.al35.dto.PurchaseOrderDTO;
import fr.eql.al35.entity.Cart;
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
		List<PurchaseOrderDTO> allPurchaseOrders = orderDelegate.getAllOrdersByUserId(userId);

		return allPurchaseOrders.stream()
				.filter(order -> order.getCreationDate()
						.isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
	}

	@Override
	public PurchaseOrderDTO getPurchaseOrderById(Integer id) {
		return orderDelegate.getOrderById(id);
	}

	@Override
	public List<PurchaseOrderDTO> getAllPurchaseOrders() {
		return orderDelegate.getAllOrders();
	}
}
