package fr.eql.al35.delegate;

import fr.eql.al35.dto.PurchaseOrderDTO;

import java.util.List;

public interface GlobalDelegate {

	void saveOrder(PurchaseOrderDTO order);

	List<PurchaseOrderDTO> getOrdersByUser(Integer userId);

	PurchaseOrderDTO getOrderById(Integer orderId);
}
