package fr.eql.al35.delegate;

import fr.eql.al35.dto.CustomDTO;
import fr.eql.al35.dto.OrderLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.dto.PurchaseOrderDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlobalDelegateImpl implements GlobalDelegate {

	private final OrderDelegate orderDelegate;
	private final ProductDelegate productDelegate;
	private final UserDelegate userDelegate;

	@Autowired
	public GlobalDelegateImpl(ProductDelegate productDelegate, OrderDelegate orderDelegate, UserDelegate userDelegate) {
		this.orderDelegate = orderDelegate;
		this.productDelegate = productDelegate;
		this.userDelegate = userDelegate;
	}

	@Override
	public void saveOrder(PurchaseOrderDTO order) {
		orderDelegate.saveOrder(order);

		order.getOrderLines()
		.forEach(line -> productDelegate.updateCloth(productDelegate.getClothById(line.getClothId())));
	}

	@Override
	public List<PurchaseOrderDTO> getOrdersByUser(Integer userId) {
		List<PurchaseOrderDTO> allPurchaseOrders = orderDelegate.getAllOrdersByUserId(userId);
		for (PurchaseOrderDTO order : allPurchaseOrders) {
			getClothForOrderLine(order);
		}

		return allPurchaseOrders.stream()
				.filter(order -> order.getCreationDate()
						.isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
	}

	@Override
	public PurchaseOrderDTO getOrderById(Integer orderId) {
		PurchaseOrderDTO purchaseOrder = orderDelegate.getOrderById(orderId);
		getClothForOrderLine(purchaseOrder);
		purchaseOrder.setDeliveryAddress(userDelegate.getAddressById(purchaseOrder.getDeliveryAddressId()));
		purchaseOrder.setBillingAddress(userDelegate.getAddressById(purchaseOrder.getBillingAddressId()));

		return purchaseOrder;
	}

	private void getClothForOrderLine(PurchaseOrderDTO order) {
			for (OrderLineDTO orderLine : order.getOrderLines()) {
				orderLine.setCloth(productDelegate.getClothById(orderLine.getClothId()));
				for (CustomDTO custom : orderLine.getCustoms()) {
					custom.setDesign(productDelegate.getDesignById(custom.getDesignId()));
				}
			}

	}
}
