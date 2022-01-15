package fr.eql.al35.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.dto.PurchaseOrderDTO;

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
}
