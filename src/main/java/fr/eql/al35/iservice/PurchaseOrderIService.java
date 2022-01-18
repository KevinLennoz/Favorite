package fr.eql.al35.iservice;

import java.util.ArrayList;
import java.util.List;

import fr.eql.al35.dto.Cart;
import fr.eql.al35.dto.OrderLineForProductWSDTO;
import fr.eql.al35.dto.PurchaseOrderDTO;

public interface PurchaseOrderIService {
	
	PurchaseOrderDTO createPurchaseOrder(Cart cart, PurchaseOrderDTO order);
	
	void savePurchaseOrder(PurchaseOrderDTO order);
	
	List<PurchaseOrderDTO> getAllByUserId(Integer userId);
	
	PurchaseOrderDTO getPurchaseOrderById(Integer id);
	
	List<PurchaseOrderDTO> getAllPurchaseOrders();

    ArrayList<OrderLineForProductWSDTO> updateStocks(List<OrderLineForProductWSDTO> orderLines);

}
