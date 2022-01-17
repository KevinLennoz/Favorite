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

	@Autowired
	public GlobalDelegateImpl(ProductDelegate productDelegate, OrderDelegate orderDelegate) {
		this.orderDelegate = orderDelegate;
		this.productDelegate = productDelegate;
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
			for (OrderLineDTO orderLine  : order.getOrderLines()) {
				orderLine.setCloth(productDelegate.getClothById(orderLine.getClothId()));
				for (CustomDTO custom : orderLine.getCustoms()) {
					custom.setDesign(productDelegate.getDesignById(custom.getDesignId()));
				}
			}
		}

		return allPurchaseOrders.stream()
				.filter(order -> order.getCreationDate()
						.isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
	}
}
