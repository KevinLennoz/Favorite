package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.dto.PurchaseOrderDTO;

public interface OrderDelegate {

	List<PurchaseOrderDTO> getAllOrdersByUserId(Integer userId);
	
	PurchaseOrderDTO getOrderById(Integer id);
	
	List<PurchaseOrderDTO> getAllOrders();
	
	PurchaseOrderDTO saveOrder(PurchaseOrderDTO order);

}
